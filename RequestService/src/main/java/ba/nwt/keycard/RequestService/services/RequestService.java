package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.clients.RoomClient;
import ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RequestService.models.Request.Request;
import ba.nwt.keycard.RequestService.models.Request.RequestDTO;
import ba.nwt.keycard.RequestService.models.Request.RequestResponseDTO;
import ba.nwt.keycard.RequestService.models.Request.RequestStatus;
import ba.nwt.keycard.RequestService.models.Team.TeamDTO;
import ba.nwt.keycard.RequestService.models.Team.TeamMapper;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.models.User.UserMapper;
import ba.nwt.keycard.RequestService.models.dtos.RoomDTO;
import ba.nwt.keycard.RequestService.models.dtos.TempAccessGrantDTO;
import ba.nwt.keycard.RequestService.repositories.RequestRepository;
import ba.nwt.keycard.RequestService.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public List<RequestResponseDTO> getAllRequests() {

        List<Request> requests = requestRepository.findAll();
        return requests.stream().map(request -> {
            RoomDTO roomDTO = roomClient.getRoomById(request.getRoomId()).orElse(null);
            TeamDTO teamDTO = teamMapper.toDTO(request.getUser().getTeam());
            UserDTO userDTO = userMapper.toDTO(request.getUser());
            return new RequestResponseDTO(request.getId(), request.getStatus(), roomDTO, userDTO, teamDTO);
        }).collect(Collectors.toList());
    }

    public RequestResponseDTO getRequestById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            RoomDTO roomDTO = roomClient.getRoomById(request.getRoomId()).orElse(null);
            TeamDTO teamDTO = teamMapper.toDTO(request.getUser().getTeam());
            UserDTO userDTO = userMapper.toDTO(request.getUser());
            return new RequestResponseDTO(request.getId(), request.getStatus(), roomDTO, userDTO, teamDTO);
        }
        return null;
    }

    @Transactional
    public Request createRequest(@Valid RequestDTO requestDTO) {
        Optional<User> userOptional = userRepository.findById(requestDTO.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<?> roomOptional = roomClient.getRoomById(requestDTO.getRoomId());
            if (roomOptional.isPresent()) {
                Request request = new Request(requestDTO.getRoomId(), user);
                return requestRepository.save(request);
            } else {
                throw new IllegalArgumentException("Room with ID " + requestDTO.getRoomId() + " does not exist.");
            }
        } else {
            throw new IllegalArgumentException("User with ID " + requestDTO.getUserId() + " does not exist.");
        }
    }

    @Transactional
    public boolean deleteRequestById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            requestRepository.delete(request);
            return true;
        }
        return false;
    }

    private final RoomClient roomClient;

    @Autowired
    public RequestService(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    public List<RequestResponseDTO> getAllRequestsForUser(Long userId) {
        List<Request> requests = requestRepository.findByUser_Id(userId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Long> roomIds = requests.stream().map(Request::getRoomId).collect(Collectors.toList());
            List<RoomDTO> roomDTOS = roomClient.fetchRoomsByIds(roomIds);

            return requests.stream().map(request -> {
                RoomDTO roomDTO = roomDTOS.stream().filter(r -> r.getId().equals(request.getRoomId())).findFirst()
                        .orElse(null);
                UserDTO userDTO = userMapper.toDTO(user);
                TeamDTO teamDTO = teamMapper.toDTO(user.getTeam());
                return new RequestResponseDTO(request.getId(), request.getStatus(), roomDTO, userDTO, teamDTO);
            }).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
    }

    @Transactional
    public boolean updateRequestStatus(Long id, RequestStatus newStatus) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            request.setStatus(newStatus);
            requestRepository.save(request);
            System.out.println(newStatus);
            // ovdje treba zamijeniti rabbitmq (sendMessage) sa proxy da se salje poruka na
            // room service
            sendMessage(request);
            return true;
        } else {
            return false;
        }
    }

    private void sendMessage(Request request) {
        LocalDate timestamp = LocalDate.now();
        TempAccessGrantDTO message = new TempAccessGrantDTO(request.getRoomId(), request.getUser().getId(),
                timestamp);

        // Send the message to the specified exchange with a routing key
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}

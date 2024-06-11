package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.clients.RoomClient;
import ba.nwt.keycard.RequestService.models.Request.Request;
import ba.nwt.keycard.RequestService.models.Request.RequestDTO;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.RequestRepository;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    public Request getRequestById(Long id){
        Optional<Request> request = requestRepository.findById(id);
        return request.orElse(null);
    }

    public Request createRequest(RequestDTO requestDTO){
        Optional<User> userOptional = userRepository.findById(requestDTO.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Request request = new Request(requestDTO.getRoomId(), user);
            return requestRepository.save(request);
        } else {
            throw new IllegalArgumentException("User with ID " + requestDTO.getUserId() + " does not exist.");
        }
    }

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
    public RequestService(RoomClient roomClient){
        this.roomClient = roomClient;
    }

    public List<?> getAllRoomsForUser(Long userId) {
            List<Long> roomIds = requestRepository.findByUser_Id(userId)
                    .stream()
                    .map(Request::getRoomId)
                    .collect(Collectors.toList());
            System.out.println(roomIds);
            return roomClient.fetchRoomsByIds(roomIds);
    }
}

package ba.nwt.keycard.RequestService.models.Request;

import ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    private final UserRepository userRepository;
    @Autowired
    public RequestMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Long roomId, String entryType, Long userId, String description
    public RequestDTO toDTO(Request request) {
        RequestDTO dto = new RequestDTO(request.getRoomId(), request.getUser().getId(), request.getStatus());
        return dto;
    }

    public Request toEntity(RequestDTO dto) {
        Request request = new Request();
        request.setRoomId(dto.getRoomId());
        request.setStatus(dto.getStatus());
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
        request.setUser(user);
        return request;
    }
}

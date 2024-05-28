package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.clients.PermissionServiceClient;
import ba.nwt.keycard.RequestService.models.Keycard;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.models.User.UserMapper;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import ba.nwt.keycard.RequestService.models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User createUser(UserDTO user) {
        return userRepository.save(userMapper.mapToUser(user));
    }

    public boolean deleteUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    private final PermissionServiceClient permissionServiceClient;

    @Autowired
    public UserService(PermissionServiceClient permissionServiceClient) {
        this.permissionServiceClient = permissionServiceClient;
    }

    public List<String> getUserPermissions(Integer keycard) {
        return permissionServiceClient.getPermissionsByKeycardId(keycard);
    }

    public Long getKeycardIdByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getKeycard().getId();
    }
}

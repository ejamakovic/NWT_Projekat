package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.clients.KeycardPermissionClient;
import ba.nwt.keycard.RequestService.models.Team.Team;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.models.User.UserMapper;
import ba.nwt.keycard.RequestService.repositories.TeamRepository;
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

    @Autowired
    private TeamRepository teamRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User createUser(UserDTO user) {
        return userRepository.save(userMapper.toEntity(user));
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

    private final KeycardPermissionClient keycardPermissionClient;

    @Autowired
    public UserService(KeycardPermissionClient keycardPermissionClient) {
        this.keycardPermissionClient = keycardPermissionClient;
    }

    public List<?> getPermissionsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user);
        Integer keycardId = Math.toIntExact(user.getKeycard().getId());

        return keycardPermissionClient.getAllPermissionsByKeycardId(keycardId);
    }

    public User setUserTeamId(Long userId, Long teamId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Team team = teamRepository.findById(teamId).orElse(null);
            if (team != null) {
                user.setTeam(team);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    public Long getUserIdByCardId(Long keycardId) {
        Long userId = userRepository.getUserIdByCardId(keycardId);
        return userId;
    }
}

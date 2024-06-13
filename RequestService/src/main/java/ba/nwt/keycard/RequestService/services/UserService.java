package ba.nwt.keycard.RequestService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ba.nwt.keycard.RequestService.clients.KeycardClient;
import ba.nwt.keycard.RequestService.controllers.ErrorHandler.CustomExceptions.ResourceNotFoundException;
import ba.nwt.keycard.RequestService.models.KeycardDTO;
import ba.nwt.keycard.RequestService.models.Team.Team;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.models.User.UserMapper;
import ba.nwt.keycard.RequestService.repositories.TeamRepository;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    private final KeycardClient keycardClient;

    @Autowired
    public UserService(KeycardClient keycardClient) {
        this.keycardClient = keycardClient;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
        {
            throw new IllegalArgumentException("User not found");
        }
        return user.get();
    }

    public User createUser(UserDTO user) {
        return userRepository.save(userMapper.toEntity(user));
    }

    @Transactional
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

    public List<?> getPermissionsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        System.out.println(user);
        Integer keycardId = Math.toIntExact(user.getKeycardId());

        return keycardClient.getAllPermissionsByKeycardId(keycardId);
    }

    @Transactional
    public User updateTeamId(Long userId, Long teamId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user != null) {
            Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
            if (team != null) {
                user.setTeam(team);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    @Transactional
    public Long getUserIdByCardId(Long keycardId) {
        Long userId = userRepository.getUserIdByCardId(keycardId);
        return userId;
    }

    @Transactional
    public User updateKeycard(Long userId, Long keycardId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<KeycardDTO> keycardDTOOptional = null;
        try {
            keycardDTOOptional = keycardClient.getKeycard(Math.toIntExact(keycardId));
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Keycard not found with id: " + keycardId);
        }
        KeycardDTO keycardDTO = keycardDTOOptional.get();
        System.out.println(keycardDTO);
        user.setKeycardId(keycardId);
        return userRepository.save(user);

    }

    public Long getKeycardIdByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getKeycardId();
        }
        return null;
    }

    public List<User> getUsersByRoles(List<String> roles) {
            return userRepository.findByRoleIn(roles);
    }
}

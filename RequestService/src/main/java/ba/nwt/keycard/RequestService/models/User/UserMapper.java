package ba.nwt.keycard.RequestService.models.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserMapper {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User toEntity(@Valid UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setActive(userDTO.getActive());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPasswordHash());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());
        return dto;
    }
}

package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.LoginResponse;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.dtos.LoginUserDto;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse authenticate(LoginUserDto loginDto) {
        logger.info("Attempting authentication for username: {}", loginDto.getUsername());

        // Retrieve the user from the database
        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Verify the password
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
                logger.info("Password verification succeeded for username: {}", loginDto.getUsername());
                // Generate JWT token
                String token = jwtService.generateToken(user);
                // Extract user roles
                String role = user.getRole();
                // Return LoginResponse with token and user information
                return new LoginResponse(token, jwtService.getExpirationTime(), user.getUsername(), role);
            } else {
                logger.warn("Password verification failed for username: {}", loginDto.getUsername());
            }
        } else {
            logger.warn("User not found for username: {}", loginDto.getUsername());
        }
        // Throw exception if authentication fails
        throw new RuntimeException("Invalid credentials");
    }
}

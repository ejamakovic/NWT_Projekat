package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.dtos.LoginUserDto;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // Inject JwtService

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService; // Initialize JwtService
    }

    public String authenticate(LoginUserDto loginDto) {
        logger.info("Attempting authentication for username: {}", loginDto.getUsername());

        // Retrieve the user from the database
        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Verify the password
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
                logger.info("Password verification succeeded for username: {}", loginDto.getUsername());

                // Generate JWT token upon successful authentication
                return jwtService.generateToken(user);
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

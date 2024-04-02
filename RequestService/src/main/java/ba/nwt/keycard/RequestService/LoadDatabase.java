package ba.nwt.keycard.RequestService;

import ba.nwt.keycard.RequestService.models.*;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.models.User.UserDTO;
import ba.nwt.keycard.RequestService.models.User.UserMapper;
import ba.nwt.keycard.RequestService.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Configuration
public class LoadDatabase {

    @Autowired
    private UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    @Transactional
    CommandLineRunner initData(UserRepository userRepository, KeycardRepository keycardRepository,
                               TeamRepository teamRepository, LogRepository logRepository,
                               NotificationRepository notificationRepository, RequestRepository requestRepository) {
        return args -> {
//
//            // Create users with keycards
//            UserDTO userDTO1 = new UserDTO("username1", "email1@example.com", "Ovo0no$1", "role1", true);
//            UserDTO userDTO2 = new UserDTO("username2", "email2@example.com", "Ovo0no$2", "role2", true);
//            User user1 = userMapper.mapToUser(userDTO1);
//            User user2 = userMapper.mapToUser(userDTO2);
//            userRepository.saveAll(Arrays.asList(user1,user2));
//
//            user1 = userRepository.getReferenceById(user1.getId());
//            user2 = userRepository.getReferenceById(user2.getId());
//
//            // Add logs associated with users
//            Log log1 = new Log(LocalDate.now(), "entryType1", user1, "description1", 1L);
//            Log log2 = new Log(LocalDate.now(), "entryType2", user2, "description2", 2L);
//            logRepository.saveAll(Arrays.asList(log1, log2));
//
//            // Add notifications associated with users
//            Notification notification1 = new Notification(user1, "message1");
//            Notification notification2 = new Notification(user2, "message2");
//            notificationRepository.saveAll(Arrays.asList(notification1, notification2));
//
//            notificationRepository.delete(notification1);
//            userRepository.delete(user1);
//
        };
    }
}

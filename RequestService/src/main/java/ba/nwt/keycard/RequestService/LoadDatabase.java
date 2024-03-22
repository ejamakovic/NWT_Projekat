package ba.nwt.keycard.RequestService;

import ba.nwt.keycard.RequestService.models.*;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initData(UserRepository userRepository, KeycardRepository keycardRepository,
                               TeamRepository teamRepository, LogRepository logRepository,
                               NotificationRepository notificationRepository, RequestRepository requestRepository) {
        return args -> {
            // Create keycards
            Keycard keycard1 = new Keycard(true);
            Keycard keycard2 = new Keycard(false);

            // Save keycards and obtain their IDs
            keycard1 = keycardRepository.save(keycard1);
            keycard2 = keycardRepository.save(keycard2);

            // Create teams
            Team team1 = new Team("Team 1");
            Team team2 = new Team("Team 2");
            // Save keycards and obtain their IDs
            team1 = teamRepository.save(team1);
            team2 = teamRepository.save(team2);
            // Now keycard1 and keycard2 will have their IDs generated by the database

            // Create users with keycards
            User user1 = new User("username1", "email1@example.com", "Ovo0no$1", "role1", true, keycard1, team1);
            User user2 = new User("username2", "email2@example.com", "Ovo0no$2", "role2", true, keycard2, team2);

            userRepository.saveAll(Arrays.asList(user1, user2));

            team1.setManagerId(user1.getId());
            team2.setManagerId(user2.getId());
            team1 = teamRepository.save(team1);
            team2 = teamRepository.save(team2);
            // Add logs
            Log log1 = new Log(LocalDate.now(), "entryType1", user1, "description1");
            Log log2 = new Log(LocalDate.now(), "entryType2", user2, "description2");
            logRepository.saveAll(Arrays.asList(log1, log2));

            // Add notifications
            Notification notification1 = new Notification(user1, "message1");
            Notification notification2 = new Notification(user2, "message2");
            notificationRepository.saveAll(Arrays.asList(notification1, notification2));

            // Add requests
            Request request1 = new Request(user1);
            Request request2 = new Request(user2);
            requestRepository.saveAll(Arrays.asList(request1, request2));

        };
    }
}
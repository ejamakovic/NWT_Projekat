package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Notification.Notification;
import ba.nwt.keycard.RequestService.models.Notification.NotificationDTO;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.repositories.NotificationRepository;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id){
        Optional<Notification> request = notificationRepository.findById(id);
        return request.orElse(null);
    }

    @Transactional
    public Notification createNotification(@Valid NotificationDTO notificationDTO){
        Optional<User> user = userRepository.findById(notificationDTO.getUserId());
        if(user.isPresent()){
            Notification notification = new Notification();
            notification.setUser(user.get());
            notification.setMessage(notificationDTO.getMessage());
            return notificationRepository.save(notification);
        }
        else{
            throw new IllegalArgumentException("User with " + notificationDTO.getUserId() + " id doesn't exist!");
        }
    }

    @Transactional
    public boolean deleteNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notificationRepository.delete(notification);
            return true;
        }
        return false;
    }
}

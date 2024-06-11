package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Notification;
import ba.nwt.keycard.RequestService.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id){
        Optional<Notification> request = notificationRepository.findById(id);
        return request.orElse(null);
    }

    public Notification createNotification(Notification notification){
        return notificationRepository.save(notification);
    }

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

package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Notification.Notification;
import ba.nwt.keycard.RequestService.models.Notification.NotificationDTO;
import ba.nwt.keycard.RequestService.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(){
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id){
        return new ResponseEntity<>(notificationService.getNotificationById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody NotificationDTO notificationDTO){
        return new ResponseEntity<>(notificationService.createNotification(notificationDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotificationById(@PathVariable Long id) {
        boolean deleted = notificationService.deleteNotificationById(id);
        if (deleted) {
            return ResponseEntity.ok("Notification with id " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found with id: " + id);
        }
    }

}

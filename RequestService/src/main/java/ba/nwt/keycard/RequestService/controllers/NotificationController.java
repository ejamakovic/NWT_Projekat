package ba.nwt.keycard.RequestService.controllers;

import ba.nwt.keycard.RequestService.models.Notification;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.services.NotificationService;
import ba.nwt.keycard.RequestService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id){
        return new ResponseEntity<>(notificationService.getNotificationById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody Notification notification, @PathVariable Long id){
        User user = userService.getUserById(id);
        notification.setUser(user);
        return new ResponseEntity<>(notificationService.createNotification(notification), HttpStatus.OK);
    }

}

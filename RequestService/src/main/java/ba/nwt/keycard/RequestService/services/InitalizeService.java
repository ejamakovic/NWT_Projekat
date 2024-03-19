package ba.nwt.keycard.RequestService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ba.nwt.keycard.RequestService.repositories.*;
import ba.nwt.keycard.RequestService.models.*;

@Service
public class InitalizeService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }
    public void saveLog(Log log){
        logRepository.save(log);
    }
    public void saveRequest(Request request){
        requestRepository.save(request);
    }
    public void saveNotification(Notification notification){
        notificationRepository.save(notification);
    }
}
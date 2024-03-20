package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.repositories.UserRepository;
import ba.nwt.keycard.RequestService.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers(){

        return userRepository.findAll();
    }

}

package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Keycard;
import ba.nwt.keycard.RequestService.models.User.User;
import ba.nwt.keycard.RequestService.producers.MessageProducer;
import ba.nwt.keycard.RequestService.repositories.KeycardRepository;
import ba.nwt.keycard.RequestService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KeycardService {

    @Autowired
    private KeycardRepository keycardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageProducer messageProducer;

    @Transactional
    public Keycard saveOrUpdateKeycard(Keycard keycard) {
        Keycard savedKeycard = keycardRepository.save(keycard);
        messageProducer.sendMessage(savedKeycard);
        return savedKeycard;
    }

    public List<Keycard> getAllKeycards() {
        return keycardRepository.findAll();
    }

    public Keycard getKeycardById(Long id) {
        Optional<Keycard> keycard = keycardRepository.findById(id);
        return keycard.orElse(null);
    }

    public Keycard createKeycard(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Keycard keycard = new Keycard();
            keycard.setUser(user);
            keycard.setIsActive(true);
            return keycardRepository.save(keycard);
        }
        return null;
    }

    public boolean deleteKeycardById(Long id) {
        Optional<Keycard> keycardOptional = keycardRepository.findById(id);
        if (keycardOptional.isPresent()) {
            Keycard keycard = keycardOptional.get();
            keycardRepository.delete(keycard);
            return true;
        }
        return false;
    }

}

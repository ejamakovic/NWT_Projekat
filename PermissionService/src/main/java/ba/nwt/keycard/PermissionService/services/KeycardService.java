package ba.nwt.keycard.PermissionService.services;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.producers.MessageProducer;
import ba.nwt.keycard.PermissionService.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeycardService {

    private final KeycardRepository keycardRepository;

    private final MessageProducer messageProducer;


    @Autowired
    public KeycardService(KeycardRepository keycardRepository, MessageProducer messageProducer) {
        this.keycardRepository = keycardRepository;
        this.messageProducer = messageProducer;
    }

    @Transactional
    public Keycard saveOrUpdateKeycard(Keycard keycard) {
        Keycard savedKeycard = keycardRepository.save(keycard);
        messageProducer.sendMessage(savedKeycard);
        return savedKeycard;
    }


    public List<Keycard> getAllKeycards() {
        return keycardRepository.findAll();
    }

    public Keycard getKeycardById(Integer id) {
        return keycardRepository.findById(id).orElse(null);
    }

    public void updateActiveStatus(Integer id, Boolean active) {
        Keycard keycard = keycardRepository.findById(id).orElse(null);
        if (keycard != null) {
            keycard.setActive(active);
            keycardRepository.save(keycard);
        }
    }

    public void deleteKeycard(Integer id) {
        keycardRepository.deleteById(id);
    }

    public Keycard createKeycard(Keycard keycard) {
        return keycardRepository.save(keycard);
    }
}

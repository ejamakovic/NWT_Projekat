package ba.nwt.keycard.PermissionService.services;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycardService {

    private final KeycardRepository keycardRepository;

    @Autowired
    public KeycardService(KeycardRepository keycardRepository) {
        this.keycardRepository = keycardRepository;
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

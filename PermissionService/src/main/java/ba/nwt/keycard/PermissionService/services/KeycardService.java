package ba.nwt.keycard.PermissionService.services;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeycardService {

    private final KeycardRepository keycardRepository;

    @Autowired
    public KeycardService(KeycardRepository keycardRepository) {
        this.keycardRepository = keycardRepository;
    }

    public void initializeKeycards() {
        // Add some sample keycards to the database
        Keycard keycard1 = new Keycard();
        keycard1.setIsActive(true);
        keycardRepository.save(keycard1);

        Keycard keycard2 = new Keycard();
        keycard2.setIsActive(true);
        keycardRepository.save(keycard2);
    }
}

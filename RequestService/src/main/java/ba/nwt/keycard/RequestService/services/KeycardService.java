package ba.nwt.keycard.RequestService.services;

import ba.nwt.keycard.RequestService.models.Keycard;
import ba.nwt.keycard.RequestService.repositories.KeycardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KeycardService {

    @Autowired
    private KeycardRepository keycardRepository;


    public List<Keycard> getAllKeycards(){
        return keycardRepository.findAll();
    }

    public Keycard getKeycardById(Long id){
        Optional<Keycard> keycard = keycardRepository.findById(id);
        return keycard.orElse(null);
    }

    public Keycard createKeycard(Keycard keycard){
        return keycardRepository.save(keycard);
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

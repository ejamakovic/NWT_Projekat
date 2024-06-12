package ba.nwt.keycard.PermissionService.producers;

import ba.nwt.keycard.PermissionService.models.Keycard;
import ba.nwt.keycard.PermissionService.repositories.KeycardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @Autowired
    private KeycardRepository keycardRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "keycardQueue")
    public void receiveMessage(String message) {
        try {
            Keycard keycard = objectMapper.readValue(message, Keycard.class);
            keycardRepository.save(keycard);  // Save or update keycard in the local database
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


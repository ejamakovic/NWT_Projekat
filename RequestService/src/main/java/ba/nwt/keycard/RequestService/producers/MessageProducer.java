package ba.nwt.keycard.RequestService.producers;
import ba.nwt.keycard.RequestService.models.Keycard;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Keycard keycard) {
        try {
            String message = objectMapper.writeValueAsString(keycard);
            rabbitTemplate.convertAndSend("keycardQueue", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


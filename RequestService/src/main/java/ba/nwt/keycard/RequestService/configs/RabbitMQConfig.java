package ba.nwt.keycard.RequestService.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue keycardQueue() {
        return new Queue("keycardQueue", true);
    }
}

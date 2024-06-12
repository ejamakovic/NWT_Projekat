package ba.nwt.keycard.RoomService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
    @Bean
    Queue queue() {
        return new Queue("roomAccessQueue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("roomAccessExchange");
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("roomAccessKey");
    }
}

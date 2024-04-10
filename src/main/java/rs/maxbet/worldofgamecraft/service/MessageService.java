package rs.maxbet.worldofgamecraft.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.data.transport.UserRepresentation;

@Service
public class MessageService {
    
    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.registration}")
    String exchangeRegistration;

    public MessageService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishUserRegistration(UserRepresentation user) {
        amqpTemplate.convertAndSend(exchangeRegistration, "",  user);
    }
}
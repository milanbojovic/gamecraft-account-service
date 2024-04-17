package rs.maxbet.worldofgamecraft.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.data.transport.UserRegistrationEvent;

import java.util.logging.Logger;

@Service
public class MessageService {

    private final Logger logger = Logger.getLogger(MessageService.class.getName());
    
    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange.registration}")
    String exchangeRegistration;

    public MessageService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishUserRegistration(UserRegistrationEvent user) {
        logger.info("Publishing user registration event to exchange: " + exchangeRegistration + " with user: " + user);
        amqpTemplate.convertAndSend(exchangeRegistration, "",  user);
    }
}
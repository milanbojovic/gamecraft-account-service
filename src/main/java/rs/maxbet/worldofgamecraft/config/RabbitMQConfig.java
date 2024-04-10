package rs.maxbet.worldofgamecraft.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.character.registration}")
    String queueCharacterUserRegistration;

    @Value("${rabbitmq.queue.combat.registration}")
    String queueCombatUserRegistration;

    @Value("${rabbitmq.exchange.registration}")
    String exchangeUserRegistration;


    @Value("${rabbitmq.queue.character.creation}")
    String queueCharacterCreation;

    @Value("${rabbitmq.queue.combat.outcome}")
    String queueCombatOutcome;

    @Bean
    FanoutExchange exchangeUserRegistration() {
        return new FanoutExchange(exchangeUserRegistration);
    }

    @Bean
    Queue queueCharacterRegistration() {
        return new Queue(queueCharacterUserRegistration, false);
    }

    @Bean
    Queue queueCombatUserRegistration() {
        return new Queue(queueCombatUserRegistration, false);
    }

    @Bean
    Binding bindingUserRegistration1(Queue queueCharacterRegistration, FanoutExchange exchangeUserRegistration) {
        return BindingBuilder.bind(queueCharacterRegistration).to(exchangeUserRegistration);
    }

    @Bean
    Binding bindingUserRegistration2(Queue queueCombatUserRegistration, FanoutExchange exchangeUserRegistration) {
        return BindingBuilder.bind(queueCombatUserRegistration).to(exchangeUserRegistration);
    }

    @Bean
    Queue queueCharacterCreation() {
        return new Queue(queueCharacterCreation, false);
    }

    @Bean
    Queue queueCombatOutcome() {
        return new Queue(queueCombatOutcome, false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
package it61.springlabs.vpsservice;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory("rabbit-mq", 5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(producerJackson2MessageConverter());
        return template;
    }

    @Bean
    public Queue CreateTicketQueue() {
        return new Queue("CreateTicket");
    }

    @Bean
    public Queue CloseTicketQueue() {
        return new Queue("CloseTicket");
    }

    @Bean
    public Queue GetTicketQueue() {
        return new Queue("GetVps");
    }

    @Bean
    public Queue HelloWorldQueue() {
        return new Queue("HelloWorld");
    }

    @Bean
    public Queue LogQueue(){return new Queue("logs");}

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

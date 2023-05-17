package br.edu.msemail.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Bean
    public Queue queue () {
        /*O parametro booleano "true" serve para que, quando
        * o rabbitMQ for interrompido, nao iremos perder nem
        * as mensagens da fila e nem a fila*/
        return new Queue(queue, true);
    }

    /*Conversor global*/
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

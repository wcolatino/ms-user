package com.microservice.user.producers;

import com.microservice.user.dtos.EmailDto;
import com.microservice.user.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*PRODUCER - responsável por produzir a mensagem de e-mail*/
@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;


    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}") //Como é um exchange Default a routing key tem que ter o mesmo nome da fila criada;
    private String routingKey;


    /* Método para enviar e-mail definindo texto e usuário que receberá a mensagem*/
    public void publishMessageEmail(User user){
        var emailDto = new EmailDto(); //EmailDto, por padrão tem de existir tanto no producer quanto no consumer
        emailDto.setUserId(user.getId());
        emailDto.setEmailTo(user.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso");
        emailDto.setText(user.getName() + ", seja bem vindo(a) \nAgradecemos os eu cadastro, aproveite todos os recursos da nossa plataforma! ");

        rabbitTemplate.convertAndSend("",routingKey, emailDto); //String vazia, routingkey (nome da fila que receberá a mensagem) e o corpo do e-mail;
    }
}

package com.microservice.user.services;

import com.microservice.user.models.User;
import com.microservice.user.producers.UserProducer;
import com.microservice.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository ,UserProducer userProducer) { //Dependência injetada através do construtor - desacoplando do framework;
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional //Transactional - Para garantir o rollback em caso de erro na operação;
    public User save(User user){
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user); //passa o user para o producer que produzirá o e-mail e enviará
        return user;
    }
}

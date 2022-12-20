package br.com.adrianomenezes.apitests.config;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "Valdir", "valdir@mail.com", "123");
        User u2 = new User(null, "Luis", "luis@mail.com", "123");
        User u3 = new User(null, "Paulo", "paulo@mail.com", "123");
        User u4 = new User(null, "Andre", "andre@mail.com", "123");

        repository.saveAll(List.of(u1, u2, u3, u4));
    }
}

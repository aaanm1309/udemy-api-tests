package br.com.adrianomenezes.apitests.services.impl;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.repositories.UserRepository;
import br.com.adrianomenezes.apitests.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;


    @Override
    public User findById(Integer id) {
        Optional<User> userReturned = repository.findById(id);
//        return userReturned.orElse(new User());
        return userReturned.orElse(null);
    }
}

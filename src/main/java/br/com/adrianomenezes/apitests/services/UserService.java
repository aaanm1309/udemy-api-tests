package br.com.adrianomenezes.apitests.services;

import br.com.adrianomenezes.apitests.domain.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}

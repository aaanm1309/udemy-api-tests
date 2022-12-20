package br.com.adrianomenezes.apitests.services;

import br.com.adrianomenezes.apitests.domain.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;


public interface UserService {

    User findById(Integer id);

}

package br.com.adrianomenezes.apitests.services;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDTO findById(Integer id);

    List<UserDTO> findAll();

    UserDTO create(UserDTO dto);
}

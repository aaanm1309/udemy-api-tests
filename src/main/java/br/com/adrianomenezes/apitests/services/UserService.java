package br.com.adrianomenezes.apitests.services;

import br.com.adrianomenezes.apitests.domain.dto.UserDTO;

import java.util.List;
import java.util.Optional;


public interface UserService {

    UserDTO findById(Integer id);

    List<UserDTO> findAll();

    UserDTO create(UserDTO dto);

    void findByEmailCheck(UserDTO dto);

    UserDTO update(UserDTO dto);

    void delete(Integer id);

}

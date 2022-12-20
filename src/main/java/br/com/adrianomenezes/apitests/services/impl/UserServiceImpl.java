package br.com.adrianomenezes.apitests.services.impl;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.repositories.UserRepository;
import br.com.adrianomenezes.apitests.services.UserService;
import br.com.adrianomenezes.apitests.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public UserDTO findById(Integer id) {
        Optional<User> userReturned = repository.findById(id);
        if (userReturned == null || userReturned.isEmpty()){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        UserDTO userDtoReturned = mapper.map(userReturned, UserDTO.class);
//        return userReturned.orElse(new User());
        return userDtoReturned;
    }

    @Override
    public List<UserDTO> findAll() {

        return repository.findAll().stream()
                .map(x-> mapper.map(x, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO dto) {
        return mapper.map(repository.save(mapper.map(dto,User.class)), UserDTO.class);
    }
}

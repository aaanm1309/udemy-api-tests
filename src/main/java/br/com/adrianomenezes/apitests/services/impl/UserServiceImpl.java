package br.com.adrianomenezes.apitests.services.impl;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.repositories.UserRepository;
import br.com.adrianomenezes.apitests.services.UserService;
import br.com.adrianomenezes.apitests.services.exceptions.DataIntegrityViolationException;
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

        return userDtoReturned;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userReturned =  repository.findAll();

        if( userReturned == null || userReturned.isEmpty()){
            return null;
        }
        return (List<UserDTO>) userReturned.stream()
                .map(x-> mapper.map(x, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO dto) {
        findByEmailCheck(dto);
        return mapper.map(repository.save(mapper.map(dto,User.class)), UserDTO.class);
    }

    @Override
    public void findByEmailCheck(UserDTO dto) {
        Optional<User> user = repository.findByEmail(dto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(dto.getId())){
            throw new DataIntegrityViolationException(
                        "Email já cadastrado no sistema. Sistema não aceita email duplicados"
                    );
        }
    }

    @Override
    public UserDTO update(UserDTO dto) {
        findById(dto.getId());
        findByEmailCheck(dto);
        return mapper.map(repository.save(mapper.map(dto,User.class)), UserDTO.class);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}

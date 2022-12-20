package br.com.adrianomenezes.apitests.services.impl;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.repositories.UserRepository;
import br.com.adrianomenezes.apitests.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NOME = "Valdir";
    public static final String EMAIL = "valdir@teste.com";
    public static final String PASSWORD = "123XYZ";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;
    private Optional<UserDTO> optionalUserDTO;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    private void startUser(){
        user = new User(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NOME, EMAIL, PASSWORD));
        optionalUserDTO = Optional.of(new UserDTO(ID, NOME, EMAIL, PASSWORD));
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(mapper.map(any(), any())).thenReturn(userDTO);
        when(repository.findById(anyInt())).thenReturn(optionalUser);


        UserDTO response = service.findById(ID);

        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
//        when(mapper.map(any(), any())).thenReturn(userDTO);
        when(repository.findById(anyInt())).thenThrow(
                new ObjectNotFoundException("Objeto não encontrado"));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }


    }
    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(mapper.map(any(), any())).thenReturn(userDTO);
        when(repository.findAll()).thenReturn(List.of(user));

        List<UserDTO> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(UserDTO.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenFindAllThenReturnAListOfUsersWithNull() {
        when(repository.findAll()).thenReturn(null);

        List<UserDTO> response = service.findAll();
        assertNull(response);

    }


    @Test
    void create() {
    }

    @Test
    void findByEmailCheck() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
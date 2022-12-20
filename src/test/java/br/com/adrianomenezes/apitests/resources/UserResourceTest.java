package br.com.adrianomenezes.apitests.resources;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserResourceTest {
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;
    private Optional<UserDTO> optionalUserDTO;
    public static final Integer ID = 1;
    public static final String NOME = "Valdir";
    public static final String EMAIL = "valdir@teste.com";
    public static final String PASSWORD = "123XYZ";
    public static final int INDEX = 0;
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema. Sistema não aceita email duplicados";


    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

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

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());


    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);
        when(service.findAll()).thenReturn(list);

        ResponseEntity<List<UserDTO>> response  = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(ArrayList.class,response.getBody().getClass());
        assertEquals(UserDTO.class,response.getBody().get(INDEX).getClass());
        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());


    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
package br.com.adrianomenezes.apitests.resources;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


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
    void findById() {
    }

    @Test
    void findAll() {
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
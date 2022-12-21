package br.com.adrianomenezes.apitests.domain;

import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {
    public static final Integer ID = 1;
    public static final String NOME = "Valdir";
    public static final String EMAIL = "valdir@teste.com";
    public static final String PASSWORD = "123XYZ";

    @InjectMocks
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }
    private void startUser(){
        user = new User(ID, NOME, EMAIL, PASSWORD);

    }

    @Test
    void whenCreateUserThenReturnOK() {
//        Mockito.when(user).thenReturn(user);
        User response = new User(ID,NOME,EMAIL,PASSWORD);

        assertEquals(user.getClass(), response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

}
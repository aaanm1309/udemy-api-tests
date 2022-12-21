package br.com.adrianomenezes.apitests.services.impl;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.repositories.UserRepository;
import br.com.adrianomenezes.apitests.services.exceptions.DataIntegrityViolationException;
import br.com.adrianomenezes.apitests.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NOME = "Valdir";
    public static final String EMAIL = "valdir@teste.com";
    public static final String PASSWORD = "123XYZ";
    public static final int INDEX = 0;
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema. Sistema não aceita email duplicados";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
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
                new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try{
            service.findById(ID);
//            assertEquals("Esperado Erro no Catch","Não Gerou Erro no Catch");
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }

    }
    @Test
    void whenFindByIdThenReturnAnObjectNotFoundExceptionWithNull() {

        when(repository.findById(anyInt())).thenReturn(null);

        try{
            service.findById(ID);
            assertEquals("Esperado Erro no Catch","Não Gerou Erro no Catch");
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
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(eq(user), any())).thenReturn(userDTO);
        when(mapper.map(eq(userDTO), any())).thenReturn(user);

        UserDTO response = service.create(userDTO);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnADataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(3);
            service.create(userDTO);
            assertEquals("Esperado Erro no Catch","Não Gerou Erro no Catch");
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class,ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }

    }

    @Test
    void whenFindByEmailCheckThenReturnOK() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            service.findByEmailCheck(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class,ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }
    }

    @Test
    void whenFindByEmailCheckThenReturnADataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(3);
            service.findByEmailCheck(userDTO);
            assertEquals("Esperado Erro no Catch","Não Gerou Erro no Catch");
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class,ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }
    }


    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(eq(user), any())).thenReturn(userDTO);
        when(mapper.map(eq(userDTO), any())).thenReturn(user);

        UserDTO response = service.update(userDTO);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnADataIntegrityViolationException() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(3);
            service.update(userDTO);
            assertEquals("Esperado Erro no Catch","Não Gerou Erro no Catch");
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class,ex.getClass());
            assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA,ex.getMessage());
        }

    }

    @Test
    void delete() {
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        when(mapper.map(any(), any())).thenReturn(userDTO);
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());

    }

    @Test
    void whenDeleteThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(
                new ObjectNotFoundException("Objeto não encontrado"));
        doNothing().when(repository).deleteById(anyInt());

        try{
            service.delete(ID);
            assertEquals("Esperado Erro no Catch","Não Gerou Erro no Catch");
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }


    }
}
package br.com.adrianomenezes.apitests.resources.exceptions;

import br.com.adrianomenezes.apitests.services.exceptions.DataIntegrityViolationException;
import br.com.adrianomenezes.apitests.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "Email já cadastrado no sistema. Sistema não aceita email duplicados";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response =
                exceptionHandler.objectNotFound(
                        new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest()
                        );
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(StandardError.class,response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO,response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());
        assertEquals(StandardError.class,response.getBody().getClass());
        assertNotNull(response.getBody().getPath());
        assertNotNull(response.getBody().getTimestamp());

    }

    @Test
    void whenDataIntegrityViolationThenReturnAResponseEntity() {

        ResponseEntity<StandardError> response =
                exceptionHandler.dataIntegrityViolation(
                        new DataIntegrityViolationException(EMAIL_JA_CADASTRADO_NO_SISTEMA),
                        new MockHttpServletRequest()
                );
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(StandardError.class,response.getBody().getClass());
        assertEquals(EMAIL_JA_CADASTRADO_NO_SISTEMA,response.getBody().getError());
        assertEquals(400,response.getBody().getStatus());
        assertEquals(StandardError.class,response.getBody().getClass());
        assertNotNull(response.getBody().getPath());
        assertNotNull(response.getBody().getTimestamp());
    }
}
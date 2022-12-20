package br.com.adrianomenezes.apitests.resources;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserResource {
    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        UserDTO userDTOReturned = mapper.map(service.findById(id), UserDTO.class);
        return ResponseEntity.ok().body(userDTOReturned);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        return ResponseEntity.ok().body(
                service.findAll().stream()
                        .map(x-> mapper.map(x, UserDTO.class))
                        .collect(Collectors.toList())
        );
    }
}

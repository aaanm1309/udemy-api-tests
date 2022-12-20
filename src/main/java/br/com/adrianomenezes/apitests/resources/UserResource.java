package br.com.adrianomenezes.apitests.resources;

import br.com.adrianomenezes.apitests.domain.User;
import br.com.adrianomenezes.apitests.domain.dto.UserDTO;
import br.com.adrianomenezes.apitests.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
//        UserDTO userDTOReturned = mapper.map(service.findById(id), UserDTO.class);
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        return ResponseEntity.ok().body(
                service.findAll()
                );
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){

        UserDTO userDTOReturned = service.create(userDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTOReturned.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userDTOReturned);
//        return ResponseEntity.created(uri).build();
    }

}

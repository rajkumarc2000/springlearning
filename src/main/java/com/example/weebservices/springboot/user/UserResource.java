package com.example.weebservices.springboot.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;
    private User findOne;

    //findallUsers
    @GetMapping(path="/users")
    public List<User> findallUsers(){
        return userService.findAll();

    }

    @GetMapping(path="users/{id}")
    public EntityModel<User> findUserbyId(@PathVariable int id) {

        User result= userService.findOne(id);

        if (result==null){
            throw new UserNotFoundException("id ="+id);
        }

        EntityModel<User> resource = EntityModel.of(result);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).findallUsers());
		
		resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping(path="users/{id}")
    public void deleteUser(@PathVariable int id){

        User result= userService.delete(id);

        if (result==null){
            throw new UserNotFoundException("id ="+id);
        }

        
    }


    @PostMapping(path="/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
}
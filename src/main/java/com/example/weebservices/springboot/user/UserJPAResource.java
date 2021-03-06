package com.example.weebservices.springboot.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

    @Autowired
    private UserDaoService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private User findOne;

    //findallUsers
    @GetMapping(path="/jpa/users")
    public List<User> findallUsers(){
        return userRepository.findAll();

    }

    @GetMapping(path="/jpa/users/{id}")
    public EntityModel<User> findUserbyId(@PathVariable int id) {

        Optional<User> result= userRepository.findById(id);

        if (!result.isPresent()){
            throw new UserNotFoundException("id ="+id);
        }

        EntityModel<User> resource = EntityModel.of(result.get());
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).findallUsers());
		
		resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){

        userRepository.deleteById(id);

        
    }
    @PostMapping(path="/jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/jpa/user/{id}/posts")
    public List<Post> findAllPostUser(@PathVariable int id){
      Optional<User> result = userRepository.findById(id);
      if (!result.isPresent()){
        throw new UserNotFoundException("id ="+id);
      }

      return result.get().getPosts();

    }
    
    @PostMapping(path="/jpa/users/{id}/posts")
    public ResponseEntity<Object> savePosts( @PathVariable int id,@RequestBody Post post){
        Optional<User> result = userRepository.findById(id);
        if (!result.isPresent()){
          throw new UserNotFoundException("id ="+id);
        }
    
        User user = result.get();
        
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
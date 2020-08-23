package com.example.weebservices.springboot.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    
    @GetMapping(path = "/person/v1")
    public Person getPerson(){
        return new Person("Rajkumar Chandrasekaran");
    }

    @GetMapping(path = "/person/v2")
   public PersonV2 getPersonV2(){
       return new PersonV2(new Name("Rajkumar", "Chandrasekaran"));
   }

}
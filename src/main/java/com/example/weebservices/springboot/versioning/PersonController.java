package com.example.weebservices.springboot.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping(path = "/person/param", params = "version=1")
    public Person getPerson() {
        return new Person("Rajkumar Chandrasekaran");
    }

    @GetMapping(path = "/person/param", params = "version=2")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Rajkumar", "Chandrasekaran"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public Person getheaderPerson() {
        return new Person("Rajkumar Chandrasekaran");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getheaderPersonV2() {
        return new PersonV2(new Name("Rajkumar", "Chandrasekaran"));
    }

    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public Person getProducePersonV1() {
        return new Person("Rajkumar Chandrasekaran");
    }

    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getProducePersonV2() {
        return new PersonV2(new Name("Rajkumar", "Chandrasekaran"));
    }

}
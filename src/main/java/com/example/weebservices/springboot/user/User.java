package com.example.weebservices.springboot.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the user model")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have atleast 2 chars")
    @ApiModelProperty(notes = "Name should have more than 2 chars")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birthdate should be in the past")
    private Date birthdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User [birthdate=" + birthdate + ", id=" + id + ", name=" + name + "]";
    }

    public User(Integer id, String name, Date birthdate) {
        super();
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public User() {
    }

    
    
}

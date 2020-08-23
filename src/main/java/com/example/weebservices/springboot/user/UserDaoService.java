package com.example.weebservices.springboot.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();

    private static int userCount = 3;

    static{
        users.add(new User(1, "Rajkumar", new Date()));
        users.add(new User(2, "Kiruba", new Date()));
        users.add(new User(3, "Roni Emmanuel", new Date()));
     
    }
    
    public List<User> findAll(){
        return users;

    }

    public User save(User user){
        if (user.getId() ==null){
            userCount++;
            user.setId(Integer.valueOf(userCount));
          
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){

        for (User user:users){
            if (user.getId()==id) return user;
        }
        return null;
    }

    public User delete(int id){
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()){
            User user = (User)iter.next();
            if (user.getId()==id){
                iter.remove();
                return user;
            }
        }
        
        return null;
    }
}
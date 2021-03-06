package com.example.weebservices.springboot.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    //method to return hello world
    //URI -helloworld 
    //GET

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "helloworld")
    public String getMessage()
    {
        return "Hello World";
    }

    @GetMapping(path = "helloworldbean")
    public HelloWorldBean getMessageBean()
    {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "helloworldbean/pathvariable/{name}")
    public HelloWorldBean getMessageBean(@PathVariable String name)
    {
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

    @GetMapping(path = "helloworldintl")
    public String getMessageBeanInternational()
    {
        return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
    }
}
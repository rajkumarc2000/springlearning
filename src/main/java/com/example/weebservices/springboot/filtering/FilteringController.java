package com.example.weebservices.springboot.filtering;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public MappingJacksonValue returnBean() {
        Somebean bean = new Somebean("value 1", "value 2", "value 3");
        MappingJacksonValue jacksonvalue = new MappingJacksonValue(bean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("string1","string2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        jacksonvalue.setFilters(filterProvider);
        return jacksonvalue;
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue returnBeanList(){
        List<Somebean> beans =  Arrays.asList( new Somebean("value 1","value 2","value 3"),new Somebean("value21","value22","value23"));
        MappingJacksonValue jacksonvalue = new MappingJacksonValue(beans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("string3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        jacksonvalue.setFilters(filterProvider);
        return jacksonvalue;
    }
}
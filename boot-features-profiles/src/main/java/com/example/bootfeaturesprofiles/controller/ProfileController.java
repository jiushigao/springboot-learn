package com.example.bootfeaturesprofiles.controller;

import com.example.bootfeaturesprofiles.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Value("${person.name:李四}")//若people.name值没配则默认值为“李四”
    String name;

    @Autowired
    Person person;

    @GetMapping("/")
    public String getPerson(){
        return person.getClass().toString()+"==="+person.toString();
    }
}

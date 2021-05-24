package com.example.SpringRepetition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogRESTController {

    @GetMapping("/home/{name}&{status}")
    public String homeWithName(
            @PathVariable("name") String name,
            @PathVariable("status") Boolean status
        ) {
        return status ? "Hello " + name + " on homepage": "Twoje konto jest nieaktywne";
    }
}

package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("")
    public String getHelloWorld(){
        return helloWorldService.helloWorld();
    }
}

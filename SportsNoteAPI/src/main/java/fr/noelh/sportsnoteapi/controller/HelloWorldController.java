package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.dto.HelloWorldDTO;
import fr.noelh.sportsnoteapi.service.HelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("")
    public HelloWorldDTO getHelloWorld(){
        log.info("GET / (HelloWorld)");
        return helloWorldService.helloWorld();
    }
}

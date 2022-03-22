package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.dto.HelloWorldDTO;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public HelloWorldDTO helloWorld(){
        HelloWorldDTO helloWorldDTO = new HelloWorldDTO();
        helloWorldDTO.setHelloWorld("Hello, World!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return helloWorldDTO;
    }
}

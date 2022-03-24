package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.dto.AverageDTO;
import fr.noelh.sportsnoteapi.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("")
    public AverageDTO getAverage(){
        AverageDTO averageDTO = new AverageDTO();
        averageDTO.setAverage(calculatorService.calculateAverageStream(Arrays.asList(84.9,84.8,84.8,84.5,84.7,84.5,84.5)));
        return averageDTO;
    }

    @GetMapping("/averagelist")
    public List<Double> getAverageList(){
        List<Double> averageWeightList = calculatorService.makeAverageWeightList(
                Arrays.asList(84.9,84.8,84.8,84.5,84.7,84.5,84.5,84.2,84.4,84.0,84.0,84.2,84.4,84.3));
        return averageWeightList;
    }
}

package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.service.AverageWeightRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/average_weight_record")
@Slf4j
public class AverageWeightRecordController {

    @Autowired
    private AverageWeightRecordService averageWeightRecordService;

    @GetMapping("/list")
    public List<AverageWeightRecordDTO> getAverageWeightRecordList(){
        log.info("GET /average_weight_record/list");
        return averageWeightRecordService.getAverageWeightRecordList();
    }
}

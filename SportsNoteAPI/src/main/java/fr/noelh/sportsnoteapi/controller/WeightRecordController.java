package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.service.WeightRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weight_record")
public class WeightRecordController {

    @Autowired
    private WeightRecordService weightRecordService;

    @GetMapping("")
    public List<WeightRecord> getWeightRecordList(){
            return weightRecordService.getWeightRecordList();
    }

    @GetMapping("/average_weight_record_list")
    public List<AverageWeightRecordDTO> getAverageWeightRecordList(){
        return weightRecordService.getAverageWeightRecordListAlt2();
    }
}

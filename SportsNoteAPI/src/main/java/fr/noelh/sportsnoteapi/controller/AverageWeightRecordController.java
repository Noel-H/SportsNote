package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.mapper.WeightRecordMapper;
import fr.noelh.sportsnoteapi.service.AverageWeightRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/average_weight_record")
@Slf4j
public class AverageWeightRecordController {

    @Autowired
    private AverageWeightRecordService averageWeightRecordService;

    @Autowired
    private WeightRecordMapper weightRecordMapper;

    @GetMapping("/list")
    public List<WeightRecordDTO> getAverageWeightRecordList(){
        log.info("GET /average_weight_record/list");
        return averageWeightRecordService.getAverageWeightRecordList().stream()
                .map(weightRecord -> weightRecordMapper.weightRecordToWeightRecordDTO(weightRecord))
                .collect(Collectors.toList());
    }

    @GetMapping("/list/{dayOfWeek}")
    public List<WeightRecordDTO> getWeeklyAverageWeightRecordListByDayOfWeek(@PathVariable("dayOfWeek") DayOfWeek dayOfWeek){
        log.info("GET /average_weight_record/list/{}", dayOfWeek);
        return averageWeightRecordService.getWeeklyAverageWeightRecordListByDay(dayOfWeek).stream()
                .map(weightRecord -> weightRecordMapper.weightRecordToWeightRecordDTO(weightRecord))
                .collect(Collectors.toList());
    }
}

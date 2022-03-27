package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.service.WeightRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/weight_record")
public class WeightRecordController {

    @Autowired
    private WeightRecordService weightRecordService;

    @GetMapping("")
    public List<WeightRecord> getWeightRecordList(){
            return weightRecordService.getWeightRecordList();
    }

    @GetMapping("/{date}")
    public ResponseEntity<WeightRecord> getWeightRecordByDate(@PathVariable("date") String date){
        try {
            return ResponseEntity.ok(weightRecordService.getWeightRecordByDate(date));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (DateTimeParseException e){
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO change error type
    @PostMapping("")
    public ResponseEntity<WeightRecord> addWeightRecord(@RequestBody WeightRecordDTO weightRecordDTO){
        try {
            return ResponseEntity.ok(weightRecordService.addWeightRecord(weightRecordDTO));
        } catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/average")
    public List<AverageWeightRecordDTO> getAverageWeightRecordList(){
        return weightRecordService.getAverageWeightRecordListAlt2();
    }
}

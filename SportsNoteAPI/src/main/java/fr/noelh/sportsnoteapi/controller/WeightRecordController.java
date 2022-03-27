package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.customexception.WeightRecordAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.WeightRecordNotFoundException;
import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.service.WeightRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/weight_record")
@Slf4j
public class WeightRecordController {

    @Autowired
    private WeightRecordService weightRecordService;

    @GetMapping("/list")
    public List<WeightRecord> getWeightRecordList(){
        log.info("GET /weight_record");
            return weightRecordService.getWeightRecordList();
    }

    @GetMapping("/{date}")
    public ResponseEntity<WeightRecord> getWeightRecordByDate(@PathVariable("date") String date){
        log.info("GET /weight_record/{}", date);
        try {
            return ResponseEntity.ok(weightRecordService.getWeightRecordByDate(date));
        } catch (WeightRecordNotFoundException e){
            log.error("GET /weight_record/{} = ERROR : {}", date, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DateTimeParseException e){
            log.error("GET /weight_record/{} = ERROR : {}", date, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<WeightRecord> addWeightRecord(@RequestBody WeightRecordDTO weightRecordDTO){
        log.info("POST /weight_record/ of Date = {} & Weight {}",weightRecordDTO.getDate(), weightRecordDTO.getWeight());
        try {
            return ResponseEntity.ok(weightRecordService.addWeightRecord(weightRecordDTO));
        } catch (WeightRecordAlreadyExistException e){
            log.error("POST /weight_record/ of Date = {} & Weight {} = ERROR : {}", weightRecordDTO.getDate(), weightRecordDTO.getDate(), e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<WeightRecord> updateWeightRecord(@RequestBody WeightRecordDTO weightRecordDTO){
        log.info("PUT /weight_record/ of Date = {} & Weight {}", weightRecordDTO.getDate(), weightRecordDTO.getWeight());
        try {
            return ResponseEntity.ok(weightRecordService.updateWeightRecord(weightRecordDTO));
        } catch (WeightRecordNotFoundException e){
            log.error("PUT /weight_record/ of Date = {} & Weight {} = ERROR : {}", weightRecordDTO.getDate(), weightRecordDTO.getDate(), e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{date}")
    public ResponseEntity<WeightRecord> deleteWeightRecord(@PathVariable("date") String date){
        log.info("DELETE /weight_record/{}", date);
        try {
            return ResponseEntity.ok(weightRecordService.deleteWeightRecord(date));
        } catch (WeightRecordNotFoundException e){
            log.error("DELETE /weight_record/{} = ERROR : {}", date, e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        } catch (DateTimeParseException e){
            log.error("DELETE /weight_record/{} = ERROR : {}", date, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

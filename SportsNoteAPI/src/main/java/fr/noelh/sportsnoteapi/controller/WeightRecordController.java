package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.customexception.WeightRecordAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.WeightRecordNotFoundException;
import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.mapper.WeightRecordMapper;
import fr.noelh.sportsnoteapi.service.WeightRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weight_record")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class WeightRecordController {

    @Autowired
    private WeightRecordService weightRecordService;

    @Autowired
    private WeightRecordMapper weightRecordMapper;

    @GetMapping("/list")
    public List<WeightRecordDTO> getWeightRecordList(){
        log.info("GET /weight_record");
        return weightRecordService.getWeightRecordList().stream()
                .map(weightRecord -> weightRecordMapper.weightRecordToWeightRecordDTO(weightRecord))
                .collect(Collectors.toList());
    }

    @GetMapping("/{date}")
    public ResponseEntity<WeightRecordDTO> getWeightRecordByDate(@PathVariable("date") String date){
        log.info("GET /weight_record/{}", date);
        try {
            WeightRecordDTO weightRecordDTO = weightRecordMapper.weightRecordToWeightRecordDTO(weightRecordService.getWeightRecordByDate(date));
            return ResponseEntity.ok(weightRecordDTO);
        } catch (WeightRecordNotFoundException e){
            log.error("GET /weight_record/{} = ERROR : {}", date, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DateTimeParseException e){
            log.error("GET /weight_record/{} = ERROR : {}", date, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<WeightRecordDTO> addWeightRecord(@RequestBody WeightRecordDTO weightRecordDTO){
        log.info("POST /weight_record/ of Date = {} & Weight {}",weightRecordDTO.getDate(), weightRecordDTO.getWeight());
        try {
            WeightRecordDTO weightRecordDTO1 = weightRecordMapper.weightRecordToWeightRecordDTO(
                    weightRecordService.addWeightRecord(
                            weightRecordMapper.weightRecordDTOToWeightRecord(weightRecordDTO)));
            return ResponseEntity.ok(weightRecordDTO1);
        } catch (WeightRecordAlreadyExistException e){
            log.error("POST /weight_record/ of Date = {} & Weight {} = ERROR : {}", weightRecordDTO.getDate(), weightRecordDTO.getWeight(), e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<WeightRecordDTO> updateWeightRecord(@RequestBody WeightRecordDTO weightRecordDTO){
        log.info("PUT /weight_record/ of Date = {} & Weight {}", weightRecordDTO.getDate(), weightRecordDTO.getWeight());
        try {
            WeightRecordDTO weightRecordDTO1 = weightRecordMapper.weightRecordToWeightRecordDTO(
                    weightRecordService.updateWeightRecord(
                            weightRecordMapper.weightRecordDTOToWeightRecord(weightRecordDTO)));
            return ResponseEntity.ok(weightRecordDTO1);
        } catch (WeightRecordNotFoundException e){
            log.error("PUT /weight_record/ of Date = {} & Weight {} = ERROR : {}", weightRecordDTO.getDate(), weightRecordDTO.getWeight(), e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<WeightRecordDTO> deleteWeightRecord(@RequestBody WeightRecordDTO weightRecordDTO){
        log.info("DELETE /weight_record/ of Date = {}", weightRecordDTO.getDate());
        try {
            WeightRecordDTO weightRecordDTO1 = weightRecordMapper.weightRecordToWeightRecordDTO(
                    weightRecordService.deleteWeightRecord(
                            weightRecordMapper.weightRecordDTOToWeightRecord(weightRecordDTO)));
            return ResponseEntity.ok(weightRecordDTO1);
        } catch (WeightRecordNotFoundException e){
            log.error("DELETE /weight_record/ of Date = {} = ERROR : {}", weightRecordDTO.getDate(), e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}

package fr.noelh.sportsnoteapi.mapper;

import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

@Component
public class WeightRecordMapper {

   public WeightRecord weightRecordDTOToWeightRecord(WeightRecordDTO weightRecordDTO){
       WeightRecord weightRecord = new WeightRecord();
       weightRecord.setDate(weightRecordDTO.getDate().toInstant()
               .atZone(ZoneId.systemDefault())
               .toLocalDate());
       weightRecord.setWeight(weightRecordDTO.getWeight());
        return weightRecord;
    }

    public WeightRecordDTO weightRecordToWeightRecordDTO(WeightRecord weightRecord){
       WeightRecordDTO weightRecordDTO = new WeightRecordDTO();
       weightRecordDTO.setDate(Date.from(weightRecord.getDate()
               .atStartOfDay()
               .atZone(ZoneId.systemDefault())
               .toInstant()));
       weightRecordDTO.setWeight(weightRecord.getWeight());
       return weightRecordDTO;
    }
}

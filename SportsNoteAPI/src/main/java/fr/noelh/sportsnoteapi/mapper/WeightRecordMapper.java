package fr.noelh.sportsnoteapi.mapper;

import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class WeightRecordMapper {

   public WeightRecord WeightRecordDTOToWeightRecord(WeightRecordDTO weightRecordDTO){
       WeightRecord weightRecord = new WeightRecord();
       weightRecord.setDate(weightRecordDTO.getDate().toInstant()
               .atZone(ZoneId.systemDefault())
               .toLocalDate());
       weightRecord.setWeight(weightRecordDTO.getWeight());
        return weightRecord;
    }
}

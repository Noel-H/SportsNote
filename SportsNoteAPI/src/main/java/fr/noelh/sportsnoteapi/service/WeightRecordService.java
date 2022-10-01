package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.customexception.WeightRecordAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.WeightRecordNotFoundException;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.repository.WeightRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeightRecordService {

    @Autowired
    private WeightRecordRepository weightRecordRepository;

    public List<WeightRecord> getWeightRecordList(){
        List<WeightRecord> weightRecordList = new ArrayList<>();
        weightRecordRepository.findAll().forEach(weightRecord -> weightRecordList.add(weightRecord));
        return weightRecordList.stream()
                .sorted((o1, o2) -> o1.getDate().isAfter(o2.getDate()) ? 1 : -1)
                .collect(Collectors.toList());
    }

    public WeightRecord getWeightRecordByDate(String date) throws WeightRecordNotFoundException {
        LocalDate localDate = LocalDate.parse(date);
        return weightRecordRepository.findByDate(localDate)
                .orElseThrow(() -> new WeightRecordNotFoundException("No weight record found for the date : "+date));
    }

    public WeightRecord addWeightRecord(WeightRecord weightRecord) throws WeightRecordAlreadyExistException {
        if (weightRecordRepository.existsByDate(weightRecord.getDate())){
            throw new WeightRecordAlreadyExistException("Weight record already exist for the date : "+weightRecord.getDate());
        }
        return weightRecordRepository.save(weightRecord);
    }

    public WeightRecord updateWeightRecord(WeightRecord weightRecord) throws WeightRecordNotFoundException {
        if (!weightRecordRepository.existsByDate(weightRecord.getDate())){
            throw new WeightRecordNotFoundException("No weight record found to update for the date : "+weightRecord.getDate());
        }
        WeightRecord weightRecordEntity = weightRecordRepository.getByDate(weightRecord.getDate());
        weightRecordEntity.setWeight(weightRecord.getWeight());
        return weightRecordRepository.save(weightRecordEntity);
    }

    public WeightRecord deleteWeightRecord(String date) throws WeightRecordNotFoundException {
        LocalDate localDate = LocalDate.parse(date);
        if (!weightRecordRepository.existsByDate(localDate)){
            throw new WeightRecordNotFoundException("No weight record found to delete for the date : "+localDate);
        }
        WeightRecord weightRecord = weightRecordRepository.getByDate(localDate);
        weightRecordRepository.delete(weightRecord);
        return weightRecord;
    }
}

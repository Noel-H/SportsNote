package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.repository.WeightRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeightRecordService {

    @Autowired
    private WeightRecordRepository weightRecordRepository;

    @Autowired
    private CalculatorService calculatorService;

    public List<WeightRecord> getWeightRecordList(){
        return weightRecordRepository.findAll();
    }

    public List<AverageWeightRecordDTO> getAverageWeightRecordList(List<WeightRecord> weightRecordList) {
        List<AverageWeightRecordDTO> averageWeightRecordList = new ArrayList<>();
        List<Double> averageWeightRecord = new ArrayList<>();
        for (WeightRecord weightRecord : weightRecordList) {
            averageWeightRecord.add(weightRecord.getWeight());
        }
        averageWeightRecord = calculatorService.makeAverageWeightList(averageWeightRecord);
        for (int i = 0; i < weightRecordList.size(); i++) {
            AverageWeightRecordDTO averageWeightRecordDTO = new AverageWeightRecordDTO(
                    weightRecordList.get(i).getDate(),
                    averageWeightRecord.get(i)
            );
            averageWeightRecordList.add(averageWeightRecordDTO);
        }
        return averageWeightRecordList;
    }
}

package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AverageWeightRecordService {

    @Autowired
    private WeightRecordService weightRecordService;

    public List<AverageWeightRecordDTO> getAverageWeightRecordList() {
        List<AverageWeightRecordDTO> averageWeightRecordList;
        List<WeightRecord> weightRecordList = weightRecordService.getWeightRecordList();
        averageWeightRecordList = calculateAverageWeight(weightRecordList);
        return averageWeightRecordList;
    }

    private List<AverageWeightRecordDTO> calculateAverageWeight(List<WeightRecord> weightRecordList) {
        List<AverageWeightRecordDTO> calculatedAverageWeight = new ArrayList<>();
        for (WeightRecord weightRecord : weightRecordList) {
            AverageWeightRecordDTO averageWeightRecordDTO = new AverageWeightRecordDTO();
            List<WeightRecord> lastWeekofWeightRecord = get7LastDaysOfWeightRecord(weightRecord.getDate(), weightRecordList);
            double average = getAverage(lastWeekofWeightRecord);
            averageWeightRecordDTO.setDate(weightRecord.getDate());
            average = roundNumberTo1(average);
            averageWeightRecordDTO.setWeight(average);
            calculatedAverageWeight.add(averageWeightRecordDTO);
        }
        return calculatedAverageWeight;
    }

    private double roundNumberTo1(double number) {
        return Math.round(number*10)/10.0;
    }

    private double getAverage(List<WeightRecord> lastWeekOfWeightRecord) {
        double result = 0;
        for (WeightRecord weightRecord : lastWeekOfWeightRecord) {
            result = result+weightRecord.getWeight();
        }
        return result/lastWeekOfWeightRecord.size();
    }

    private List<WeightRecord> get7LastDaysOfWeightRecord(LocalDate date, List<WeightRecord> weightRecordList) {
        return weightRecordList.stream()
                .filter(weightRecord ->
                        weightRecord.getDate().isBefore(date.plusDays(1)) &&
                                weightRecord.getDate().isAfter(date.minusDays(7))                        )
                .collect(Collectors.toList());
    }
}

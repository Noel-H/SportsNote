package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.repository.WeightRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<AverageWeightRecordDTO> getAverageWeightRecordListAlt(List<WeightRecord> weightRecordList) {
        List<AverageWeightRecordDTO> averageWeightRecordList = new ArrayList<>();
        for (WeightRecord weightRecordElement: weightRecordList) {
            List<AverageWeightRecordDTO> temp = weightRecordList.stream()
                    .filter(weightRecord ->
                            weightRecord.getDate().isBefore(weightRecordElement.getDate().plusDays(1)) &&
                                    weightRecord.getDate().isAfter(weightRecordElement.getDate().minusDays(7)))
                    .map(weightRecord -> new AverageWeightRecordDTO(
                            weightRecord.getDate(),
                            weightRecord.getWeight()
                    ))
                    .collect(Collectors.toList());

            List<List<AverageWeightRecordDTO>> list = new ArrayList<>();
            list.add(temp);
            System.out.println(temp);
            System.out.println(temp.stream().count());
            double total=0;
            List<LocalDate> localDateList = new ArrayList<>();
            for (List<AverageWeightRecordDTO> elemList : list) {
                for (AverageWeightRecordDTO elem: elemList) {
                    total = total+elem.getWeight();
                    localDateList.add(elem.getDate());
                }
                AverageWeightRecordDTO averageWeightRecordDTO = new AverageWeightRecordDTO();
                averageWeightRecordDTO.setDate(Collections.max(localDateList));
                averageWeightRecordDTO.setWeight(total/elemList.size());
                averageWeightRecordDTO.setWeight(Math.round(averageWeightRecordDTO.getWeight()*10)/10.0);
                averageWeightRecordList.add(averageWeightRecordDTO);
            }
        }

        return averageWeightRecordList;
    }
}

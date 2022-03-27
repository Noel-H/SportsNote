package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.dto.AverageWeightRecordDTO;
import fr.noelh.sportsnoteapi.dto.WeightRecordDTO;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import fr.noelh.sportsnoteapi.repository.WeightRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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


    public List<AverageWeightRecordDTO> getAverageWeightRecordListAlt2() {
        List<AverageWeightRecordDTO> averageWeightRecordList;
        //recup la liste de poids
        List<WeightRecord> weightRecordList = getWeightRecordList();
        //Faire la moyenne des poids
        averageWeightRecordList = calculateAverageWeight(weightRecordList);
        return averageWeightRecordList;
    }

    private List<AverageWeightRecordDTO> calculateAverageWeight(List<WeightRecord> weightRecordList) {
        List<AverageWeightRecordDTO> calculatedAverageWeight = new ArrayList<>();
        // pour tous les element
        for (WeightRecord weightRecord : weightRecordList) {
            AverageWeightRecordDTO averageWeightRecordDTO = new AverageWeightRecordDTO();
            // on recup les 7 dernier jours de data a partir de la date donné
            List<WeightRecord> lastWeekofWeightRecord = get7LastDaysOfWeightRecord(weightRecord.getDate(), weightRecordList);
            // on faite la moyenne des poids présent
            double average = getAverage(lastWeekofWeightRecord);
            // save la date du dernier jour
            averageWeightRecordDTO.setDate(weightRecord.getDate());
            // on arrondi le poids a 1 chiffre apres la virgule
            average = roundNumberTo1(average);
            // save la moyenne
            averageWeightRecordDTO.setWeight(average);
            //
            calculatedAverageWeight.add(averageWeightRecordDTO);
        }
        return calculatedAverageWeight;
    }

    private double roundNumberTo1(double number) {
        return Math.round(number*10)/10.0;
    }

    private double getAverage(List<WeightRecord> lastWeekofWeightRecord) {
        //calculer la moyenne
        double result = 0;
        for (WeightRecord weightRecord : lastWeekofWeightRecord) {
            result = result+weightRecord.getWeight();
        }
        return result/lastWeekofWeightRecord.size();
    }

    private List<WeightRecord> get7LastDaysOfWeightRecord(LocalDate date, List<WeightRecord> weightRecordList) {
        return weightRecordList.stream()
                .filter(weightRecord ->
                        weightRecord.getDate().isBefore(date.plusDays(1)) &&
                                weightRecord.getDate().isAfter(date.minusDays(7))                        )
                .collect(Collectors.toList());
    }

    public WeightRecord getWeightRecordByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return weightRecordRepository.findByDate(localDate)
                .orElseThrow(() -> new NoSuchElementException("No weight record found for the : "+date));
    }

    public WeightRecord addWeightRecord(WeightRecordDTO weightRecordDTO) {
        if (weightRecordRepository.existsByDate(weightRecordDTO.getDate())){
            throw new IllegalArgumentException("Weight record already exist");
        }
        WeightRecord weightRecord = new WeightRecord();
        weightRecord.setDate(weightRecordDTO.getDate());
        weightRecord.setWeight(weightRecordDTO.getWeight());
        return weightRecordRepository.save(weightRecord);
    }
}

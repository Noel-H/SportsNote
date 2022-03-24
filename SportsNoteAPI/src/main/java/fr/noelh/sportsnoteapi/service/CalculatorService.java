package fr.noelh.sportsnoteapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {

    private int findDivider(List<Double> weeklyWeightList){
        int savedWeight = 0;
        for (Double weight : weeklyWeightList) {
            if(weight!=null){
                savedWeight++;
            }
        }
        return savedWeight;
    }

    public double calculateAverage(List<Double> weeklyWeightList){
        int divider = findDivider(weeklyWeightList);
        double dividend = calculateDividend(weeklyWeightList);
        return dividend/divider;
    }

    private double calculateDividend(List<Double> weeklyWeightList) {
        double dividend = 0;
        for (Double weight : weeklyWeightList){
            dividend = dividend+weight;
        }
        return dividend;
    }

    public double calculateAverageStream(List<Double> weeklyWeightList){
        return weeklyWeightList.stream()
                .mapToDouble((weight) -> weight)
                .average()
                .orElse(0);
    }

    public List<Double> makeAverageWeightList(List<Double> weightList){
        List<Double> averageWeightList = new ArrayList<>();
        List<Double> weeklyWeightList;
        int lastValue = -6;
        int firstValue = 1;
        for (int i = 0; i < weightList.size(); i++) {
            if (lastValue<0){
                weeklyWeightList = weightList.subList(0,firstValue);
            } else {
                weeklyWeightList = weightList.subList(lastValue,firstValue);
            }
            averageWeightList.add(Math.round(calculateAverageStream(weeklyWeightList)*10)/10.0);
            lastValue++;
            firstValue++;
        }
        return averageWeightList;
    }
}

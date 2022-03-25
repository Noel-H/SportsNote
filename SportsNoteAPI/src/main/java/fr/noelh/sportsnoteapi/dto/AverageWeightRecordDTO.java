package fr.noelh.sportsnoteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageWeightRecordDTO {
    private LocalDate date;
    private Double weight;
}

package fr.noelh.sportsnoteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AverageWeightRecordDTO {
    private LocalDateTime date;
    private Double weight;
}

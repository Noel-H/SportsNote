package fr.noelh.sportsnoteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightRecordDTO {
    private Date date;
    private double weight;
}

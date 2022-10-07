package fr.noelh.sportsnoteapi.dto;

import fr.noelh.sportsnoteapi.model.Role;
import fr.noelh.sportsnoteapi.model.WeightRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

    private String email;

    private String password;

    private List<WeightRecord> weightRecordList = new ArrayList<>();

    private Collection<Role> roles = new ArrayList<>();
}

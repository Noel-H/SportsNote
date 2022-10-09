package fr.noelh.sportsnoteapi.dto;

import fr.noelh.sportsnoteapi.enumeration.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private RoleEnum roleEnum;

}

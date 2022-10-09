package fr.noelh.sportsnoteapi.mapper;

import fr.noelh.sportsnoteapi.dto.RoleDTO;
import fr.noelh.sportsnoteapi.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role roleDTOTORole(RoleDTO roleDTO){
        Role role = new Role();
        role.setRoleEnum(roleDTO.getRoleEnum());
        return role;
    }
}

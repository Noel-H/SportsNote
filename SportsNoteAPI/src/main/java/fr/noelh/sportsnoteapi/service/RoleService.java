package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.customexception.RoleAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.RoleNotFoundException;
import fr.noelh.sportsnoteapi.model.Role;
import fr.noelh.sportsnoteapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }

    public Role addRole(Role role) throws RoleAlreadyExistException {
        if (roleRepository.findByRoleEnum(role.getRoleEnum()).isPresent()){
            throw new RoleAlreadyExistException("Role : '"+role.getRoleEnum()+"' already exist");
        }
        return roleRepository.save(role);
    }

    public Role deleteRole(Long id) throws RoleNotFoundException {
        Role roleToDelete = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role id : '"+id+"' not found"));
        Role roleToReturn = new Role();
        roleToReturn.setId(roleToDelete.getId());
        roleToReturn.setRoleEnum(roleToDelete.getRoleEnum());
        roleRepository.delete(roleToDelete);
        return roleToReturn;
    }
}

package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.customexception.RoleAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.RoleNotFoundException;
import fr.noelh.sportsnoteapi.enumeration.RoleEnum;
import fr.noelh.sportsnoteapi.model.Role;
import fr.noelh.sportsnoteapi.repository.RoleRepository;
import fr.noelh.sportsnoteapi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    private final UserAccountRepository userAccountRepository;

    public RoleService(RoleRepository roleRepository, UserAccountRepository userAccountRepository) {
        this.roleRepository = roleRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }

    public Role getRoleByRoleEnum(RoleEnum roleEnum) throws RoleNotFoundException {
        return roleRepository.findRoleByRoleEnum(roleEnum)
                .orElseThrow(() -> new RoleNotFoundException("Role : '"+roleEnum+"' not found"));
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

        userAccountRepository.findAll()
                .forEach(userAccount -> userAccount.getRoles().remove(roleToDelete));

        roleRepository.delete(roleToDelete);
        return roleToReturn;
    }
}

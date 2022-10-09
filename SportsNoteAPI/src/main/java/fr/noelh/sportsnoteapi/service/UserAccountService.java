package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.customexception.*;
import fr.noelh.sportsnoteapi.model.Role;
import fr.noelh.sportsnoteapi.model.UserAccount;
import fr.noelh.sportsnoteapi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final RoleService roleService;

    public UserAccountService(UserAccountRepository userAccountRepository, RoleService roleService){
        this.userAccountRepository = userAccountRepository;
        this.roleService = roleService;
    }

    public List<UserAccount> getUserAccountList(){
        return userAccountRepository.findAll();
    }

    public UserAccount getUserAccount(Long id) throws UserAccountNotFoundException {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount id : '"+id+"' not found"));
    }

    public UserAccount getUserAccountByEmail(String email) throws UserAccountNotFoundException {
        return userAccountRepository.findUserAccountByEmail(email)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount email : '"+email+"' not found"));
    }

    public UserAccount addUserAccount(UserAccount userAccount) throws UserAccountAlreadyExistException {
        if (userAccountRepository.findUserAccountByEmail(userAccount.getEmail()).isPresent()){
            throw new UserAccountAlreadyExistException("UserAccount email : '"+userAccount.getEmail()+"' already exist");
        }
        UserAccount userAccountToAdd = new UserAccount();
        userAccountToAdd.setEmail(userAccount.getEmail());
        userAccountToAdd.setPassword(userAccount.getPassword());
        userAccountToAdd.setWeightRecordList(userAccount.getWeightRecordList());
        userAccountToAdd.setRoles(userAccount.getRoles());
        return userAccountRepository.save(userAccountToAdd);
    }

    public UserAccount updateUserAccount(Long id, UserAccount userAccount) throws UserAccountNotFoundException {
        UserAccount userAccountToUpdate = userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount id : '"+id+"' not found"));
        userAccountToUpdate.setEmail(userAccount.getEmail().isEmpty() ? userAccountToUpdate.getEmail() : userAccount.getEmail());
        userAccountToUpdate.setPassword(userAccount.getPassword().isEmpty() ? userAccountToUpdate.getPassword() : userAccount.getPassword());
        userAccountToUpdate.setWeightRecordList(userAccount.getWeightRecordList().isEmpty() ? userAccountToUpdate.getWeightRecordList() : userAccount.getWeightRecordList());
        userAccountToUpdate.setRoles(userAccount.getRoles().isEmpty() ? userAccountToUpdate.getRoles() : userAccount.getRoles());
        return userAccountRepository.save(userAccountToUpdate);
    }

    public UserAccount deleteUserAccountByEmail(UserAccount userAccount) throws UserAccountNotFoundException {
        UserAccount userAccountToDelete = userAccountRepository.findUserAccountByEmail(userAccount.getEmail())
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount email : '"+userAccount.getEmail()+"' not found"));
        UserAccount userAccountToReturn = new UserAccount(
                userAccountToDelete.getId(),
                userAccountToDelete.getEmail(),
                userAccountToDelete.getPassword(),
                userAccountToDelete.getWeightRecordList(),
                userAccountToDelete.getRoles());
        userAccountRepository.delete(userAccountToDelete);
        return userAccountToReturn;
    }

    public UserAccount deleteUserAccount(Long id) throws UserAccountNotFoundException {
        UserAccount userAccountToDelete = userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount id : '"+id+"' not found"));
        UserAccount userAccountToReturn = new UserAccount(
                userAccountToDelete.getId(),
                userAccountToDelete.getEmail(),
                userAccountToDelete.getPassword(),
                new ArrayList<>(userAccountToDelete.getWeightRecordList()),
                new ArrayList<>(userAccountToDelete.getRoles()));
        userAccountRepository.delete(userAccountToDelete);
        return userAccountToReturn;
    }

    public UserAccount addRoleToUserAccount(Long id, Role role) throws UserAccountNotFoundException, RoleAlreadyExistForThisUserAccountException, RoleNotFoundException {
        UserAccount userAccountToAddRole = userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount id : '"+id+"' not found"));

        Role roleToAdd = roleService.getRoleByRoleEnum(role.getRoleEnum());

        if (userAccountToAddRole.getRoles().contains(roleToAdd)){
            throw new RoleAlreadyExistForThisUserAccountException("UserAccount email : '"+userAccountToAddRole.getEmail()+"' already have the role : '"+roleToAdd+"'");
        }
        userAccountToAddRole.getRoles().add(roleToAdd);
        return userAccountRepository.save(userAccountToAddRole);
    }

    public UserAccount deleteRoleToUserAccount(Long id, Role role) throws UserAccountNotFoundException, RoleNotFoundForThisUserAccountException, RoleNotFoundException {
        UserAccount userAccountToDeleteRole = userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount id : '"+id+"' not found"));

        Role roleToDelete = roleService.getRoleByRoleEnum(role.getRoleEnum());

        if (!userAccountToDeleteRole.getRoles().contains(roleToDelete)){
            throw new RoleNotFoundForThisUserAccountException("UserAccount email : '"+userAccountToDeleteRole.getEmail()+"' don't have the role : '"+roleToDelete+"'");
        }

        userAccountToDeleteRole.getRoles().remove(roleToDelete);
        return userAccountRepository.save(userAccountToDeleteRole);
    }
}

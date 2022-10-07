package fr.noelh.sportsnoteapi.service;

import fr.noelh.sportsnoteapi.customexception.UserAccountAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.UserAccountNotFoundException;
import fr.noelh.sportsnoteapi.model.UserAccount;
import fr.noelh.sportsnoteapi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public List<UserAccount> getUserAccountList(){
        return userAccountRepository.findAll();
    }

    public UserAccount getUserAccountByEmail(String email) throws UserAccountNotFoundException {
        return userAccountRepository.findUserAccountByEmail(email)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount '"+email+"' not found"));
    }

    public UserAccount addUserAccount(UserAccount userAccount) throws UserAccountAlreadyExistException {
        if (userAccountRepository.findUserAccountByEmail(userAccount.getEmail()).isPresent()){
            throw new UserAccountAlreadyExistException("UserAccount '"+userAccount.getEmail()+"' Already exist");
        }
        UserAccount userAccountToAdd = new UserAccount();
        userAccountToAdd.setEmail(userAccount.getEmail());
        userAccountToAdd.setPassword(userAccount.getPassword());
        userAccountToAdd.setWeightRecordList(userAccount.getWeightRecordList());
        userAccountToAdd.setRoles(userAccount.getRoles());
        return userAccountRepository.save(userAccountToAdd);
    }

    public UserAccount updateUserAccount(UserAccount userAccount) throws UserAccountNotFoundException {
        UserAccount userAccountToUpdate = userAccountRepository.findUserAccountByEmail(userAccount.getEmail())
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount '"+userAccount.getEmail()+"' not found"));
        userAccountToUpdate.setEmail(userAccount.getEmail().isEmpty() ? userAccountToUpdate.getEmail() : userAccount.getEmail());
        userAccountToUpdate.setPassword(userAccount.getPassword().isEmpty() ? userAccountToUpdate.getPassword() : userAccount.getPassword());
        userAccountToUpdate.setWeightRecordList(userAccount.getWeightRecordList().isEmpty() ? userAccountToUpdate.getWeightRecordList() : userAccount.getWeightRecordList());
        userAccountToUpdate.setRoles(userAccount.getRoles().isEmpty() ? userAccountToUpdate.getRoles() : userAccount.getRoles());
        return userAccountRepository.save(userAccount);
    }

    public UserAccount deleteUserAccountByEmail(UserAccount userAccount) throws UserAccountNotFoundException {
        UserAccount userAccountToDelete = userAccountRepository.findUserAccountByEmail(userAccount.getEmail())
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount '"+userAccount.getEmail()+"' not found"));
        UserAccount userAccountToReturn = new UserAccount(
                userAccountToDelete.getId(),
                userAccountToDelete.getEmail(),
                userAccountToDelete.getPassword(),
                userAccountToDelete.getWeightRecordList(),
                userAccountToDelete.getRoles());
        userAccountRepository.delete(userAccountToDelete);
        return userAccountToReturn;
    }
}

package fr.noelh.sportsnoteapi.mapper;

import fr.noelh.sportsnoteapi.dto.UserAccountDTO;
import fr.noelh.sportsnoteapi.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    public UserAccount userAccountDTOToUserAccount(UserAccountDTO userAccountDTO){
        UserAccount userAccount = new UserAccount();
//        userAccount.setId(userAccountDTO.getId());
        userAccount.setEmail(userAccountDTO.getEmail());
        userAccount.setPassword(userAccountDTO.getPassword());
        userAccount.setWeightRecordList(userAccountDTO.getWeightRecordList());
        userAccount.setRoles(userAccountDTO.getRoles());
        return userAccount;
    }

//    public UserAccountDTO userAccountToUserAccountDTO(UserAccount userAccount){
//        UserAccountDTO userAccountDTO = new UserAccountDTO();
//        userAccountDTO.setId(userAccount.getId());
//        userAccountDTO.setEmail(userAccount.getEmail());
//        userAccountDTO.setPassword(userAccount.getPassword());
//        userAccountDTO.setWeightRecordList(userAccount.getWeightRecordList());
//        userAccountDTO.setRoles(userAccount.getRoles());
//        return userAccountDTO;
//    }
}

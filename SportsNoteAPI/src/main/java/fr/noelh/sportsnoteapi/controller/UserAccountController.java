package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.customexception.UserAccountAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.UserAccountNotFoundException;
import fr.noelh.sportsnoteapi.dto.UserAccountDTO;
import fr.noelh.sportsnoteapi.mapper.UserAccountMapper;
import fr.noelh.sportsnoteapi.model.UserAccount;
import fr.noelh.sportsnoteapi.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userAccounts")
@Slf4j
public class UserAccountController {

    private final UserAccountService userAccountService;

    private final UserAccountMapper userAccountMapper;

    public UserAccountController(UserAccountService userAccountService, UserAccountMapper userAccountMapper) {
        this.userAccountService = userAccountService;
        this.userAccountMapper = userAccountMapper;
    }

    @GetMapping("")
    public List<UserAccount> getUserAccounts(){
        log.info("GET /userAccounts");
        return userAccountService.getUserAccountList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable("id") Long id){
        try {
            log.info("GET /userAccounts/{}", id);
            return ResponseEntity.ok(userAccountService.getUserAccount(id));
        } catch (UserAccountNotFoundException e){
            log.error("GET /userAccounts/{} = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<UserAccount> addUserAccount(@RequestBody UserAccountDTO userAccountDTO){
        try {
            log.info("POST /userAccounts");
            return ResponseEntity.ok(
                    userAccountService.addUserAccount(
                            userAccountMapper.userAccountDTOToUserAccount(userAccountDTO)));
        } catch (UserAccountAlreadyExistException e){
            log.error("POST /userAccounts = ERROR : {}", e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable("id") Long id,
                                                         @RequestBody UserAccountDTO userAccountDTO){
        try{
            log.info("PUT /userAccounts/{}", id);
            return ResponseEntity.ok(
                    userAccountService.updateUserAccount(
                            id,
                            userAccountMapper.userAccountDTOToUserAccount(userAccountDTO)));
        } catch (UserAccountNotFoundException e){
            log.error("PUT /userAccounts/{} = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserAccount> deleteUserAccount(@PathVariable("id") Long id){
        try {
            log.info("DELETE /userAccounts/{}", id);
            return ResponseEntity.ok(
                    userAccountService.deleteUserAccount(id));
        } catch (UserAccountNotFoundException e){
            log.info("DELETE /userAccounts/{} = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

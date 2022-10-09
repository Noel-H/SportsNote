package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.customexception.*;
import fr.noelh.sportsnoteapi.dto.RoleDTO;
import fr.noelh.sportsnoteapi.dto.UserAccountDTO;
import fr.noelh.sportsnoteapi.mapper.RoleMapper;
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

    private final RoleMapper roleMapper;

    public UserAccountController(UserAccountService userAccountService, UserAccountMapper userAccountMapper, RoleMapper roleMapper) {
        this.userAccountService = userAccountService;
        this.userAccountMapper = userAccountMapper;
        this.roleMapper = roleMapper;
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
            log.error("DELETE /userAccounts/{} = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserAccount> addRoleToUserAccount(@PathVariable Long id,
                                                            @RequestBody RoleDTO roleDTO){
        try{
            log.info("POST /{}/roles", id);
            return ResponseEntity.ok(
                    userAccountService.addRoleToUserAccount(
                            id,
                            roleMapper.roleDTOTORole(roleDTO)));
        } catch (UserAccountNotFoundException e){
            log.error("POST /{}/roles = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (RoleAlreadyExistForThisUserAccountException e){
            log.error("POST /{}/roles = ERROR : {}", id, e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        } catch (RoleNotFoundException e) {
            log.error("POST /{}/roles = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/roles")
    public ResponseEntity<UserAccount> deleteRoleToUserAccount(@PathVariable Long id,
                                                               @RequestBody RoleDTO roleDTO){
        try{
            log.info("DELETE /{}/roles", id);
            return ResponseEntity.ok(
                    userAccountService.deleteRoleToUserAccount(
                            id,
                            roleMapper.roleDTOTORole(roleDTO)
                    ));
        } catch (UserAccountNotFoundException e){
            log.error("DELETE /{}/roles = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (RoleNotFoundForThisUserAccountException e){
            log.error("DELETE /{}/roles = ERROR : {}", id, e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        } catch (RoleNotFoundException e) {
            log.error("DELETE /{}/roles = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

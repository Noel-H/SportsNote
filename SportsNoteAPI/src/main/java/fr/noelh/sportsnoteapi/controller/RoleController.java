package fr.noelh.sportsnoteapi.controller;

import fr.noelh.sportsnoteapi.customexception.RoleAlreadyExistException;
import fr.noelh.sportsnoteapi.customexception.RoleNotFoundException;
import fr.noelh.sportsnoteapi.dto.RoleDTO;
import fr.noelh.sportsnoteapi.mapper.RoleMapper;
import fr.noelh.sportsnoteapi.model.Role;
import fr.noelh.sportsnoteapi.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Slf4j
public class RoleController {

    private final RoleService roleService;

    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping("")
    public List<Role> getRoles(){
        log.info("GET /roles");
        return roleService.getRoleList();
    }

    @PostMapping("")
    public ResponseEntity<Role> addRole(@RequestBody RoleDTO roleDTO){
        try {
            log.info("POST /roles");
            return ResponseEntity.ok(
                    roleService.addRole(
                            roleMapper.roleDTOTORole(roleDTO)));
        } catch (RoleAlreadyExistException e){
            log.error("POST /roles = ERROR : {}", e.getMessage());
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id){
        try {
            log.info("DELETE /roles/{}", id);
            return ResponseEntity.ok(roleService.deleteRole(id));
        } catch (RoleNotFoundException e){
            log.error("DELETE /roles/{} = ERROR : {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

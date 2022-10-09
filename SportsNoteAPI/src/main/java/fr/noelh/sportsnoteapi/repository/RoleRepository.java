package fr.noelh.sportsnoteapi.repository;

import fr.noelh.sportsnoteapi.enumeration.RoleEnum;
import fr.noelh.sportsnoteapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<RoleEnum> findByRoleEnum(RoleEnum roleEnum);
    Optional<Role> findRoleByRoleEnum(RoleEnum roleEnum);

}

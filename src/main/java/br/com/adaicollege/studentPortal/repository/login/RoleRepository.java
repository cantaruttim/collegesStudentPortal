package br.com.adaicollege.studentPortal.repository.login;

import br.com.adaicollege.studentPortal.auth.enums.RoleName;
import br.com.adaicollege.studentPortal.auth.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository
        extends MongoRepository<
            Role,
            String
        > {

    Optional<Role> findByRoleName(RoleName roleName);
    List<Role> findByRoleNameIn(List<RoleName> names);

}

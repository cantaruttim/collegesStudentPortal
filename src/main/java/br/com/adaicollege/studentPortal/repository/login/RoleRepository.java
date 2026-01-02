package br.com.adaicollege.studentPortal.repository.login;

import br.com.adaicollege.studentPortal.auth.RoleName;
import br.com.adaicollege.studentPortal.auth.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository
        extends MongoRepository<
            Role,
            String
        > {

    Optional<Role> findByRoleName(RoleName roleName);
}

package br.com.adaicollege.studentPortal.auth.config;

import br.com.adaicollege.studentPortal.auth.enums.RoleName;
import br.com.adaicollege.studentPortal.auth.model.Role;
import br.com.adaicollege.studentPortal.auth.model.RolePermissionMap;
import br.com.adaicollege.studentPortal.repository.login.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleBootstrap {

    @Bean
    CommandLineRunner loadRoles(RoleRepository repo) {
        return args -> {

            for (RoleName roleName : RoleName.values()) {

                Role role = repo.findByRoleName(roleName)
                        .orElseGet(() -> {
                            Role r = new Role();
                            r.setRoleName(roleName);
                            return r;
                        });

                role.setPermissions(
                        RolePermissionMap.MAP.get(roleName)
                );

                repo.save(role);
            }
        };
    }

}

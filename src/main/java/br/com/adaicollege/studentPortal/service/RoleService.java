package br.com.adaicollege.studentPortal.service;

import br.com.adaicollege.studentPortal.auth.enums.RoleName;
import br.com.adaicollege.studentPortal.auth.model.Role;
import br.com.adaicollege.studentPortal.repository.login.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }

    public Role getByRoleName(RoleName roleName) {
        return repo.findByRoleName(roleName)
                .orElseThrow(() ->
                        new IllegalStateException("Role not found: " + roleName)
                );
    }

    public Role getStudentRole() {
        return repo.findByRoleName(RoleName.STUDENT)
                .orElseThrow(() -> new RuntimeException("Role STUDENT not found"));
    }

    public Role getAdminRole() {
        return repo.findByRoleName(RoleName.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
    }

    public Role getSecretariaRole() {
        return repo.findByRoleName(RoleName.SECRETARY)
                .orElseThrow(() -> new RuntimeException("Role SECRETARY not found"));
    }

}

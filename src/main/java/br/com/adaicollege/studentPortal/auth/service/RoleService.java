package br.com.adaicollege.studentPortal.auth.service;

import br.com.adaicollege.studentPortal.auth.RoleName;
import br.com.adaicollege.studentPortal.repository.login.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public RoleName getStudentRole() {
        return roleRepo.findyByRoleName(String.valueOf(RoleName.STUDENT))
                .map(role -> role.getRoleName())
                .orElseThrow(() -> new IllegalStateException("STUDENT role not found"));
    }

}

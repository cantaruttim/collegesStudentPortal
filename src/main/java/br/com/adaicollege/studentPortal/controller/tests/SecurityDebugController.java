package br.com.adaicollege.studentPortal.controller.tests;

import br.com.adaicollege.studentPortal.auth.model.Role;
import br.com.adaicollege.studentPortal.repository.login.RoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/debug")
public class SecurityDebugController {

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {

        return Map.of(
                "principal", authentication.getName(),
                "authorities", authentication.getAuthorities()
                        .stream()
                        .map(a -> a.getAuthority())
                        .toList()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-only")
    public String adminOnly() {
        return "ok";
    }

    @PreAuthorize("hasAuthority('STUDENT_UPDATE_SELF')")
    @GetMapping("/student-update")
    public String studentPermission() {
        return "ok";
    }

    @PreAuthorize("""
        hasRole('ADMIN')
        or (hasAuthority('STUDENT_UPDATE_SELF')
            and #id == authentication.name)
    """)
    @PutMapping("/students/{id}")
    public String update(@PathVariable String id) {
        return "updated";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/roles")
    public List<Role> roles(RoleRepository repo) {
        return repo.findAll();
    }
}

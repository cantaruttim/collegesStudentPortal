package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesResponse;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.UpdateModulesRequest;
import br.com.adaicollege.studentPortal.service.academic.secretary.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modules")
public class ModulesController {

    @Autowired
    private ModulesService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PostMapping
    public ResponseEntity<ModulesResponse> create(
            @RequestBody ModulesRequest request
    ) {
        ModulesResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping
    public ResponseEntity<List<ModulesResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping("/{id}")
    public ResponseEntity<ModulesResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PutMapping("/{id}")
    public ResponseEntity<ModulesResponse> update(
            @PathVariable String id,
            @RequestBody UpdateModulesRequest request
    ) {
        ModulesResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }


}

package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesResponse;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.UpdateModulesRequest;
import br.com.adaicollege.studentPortal.service.academic.secretary.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modules")
public class ModulesController {

    @Autowired
    private ModulesService service;

    @PostMapping
    public ResponseEntity<ModulesResponse> create(
            @RequestBody ModulesRequest request
    ) {
        ModulesResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ModulesResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModulesResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModulesResponse> update(
            @PathVariable String id,
            @RequestBody UpdateModulesRequest request
    ) {
        ModulesResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }


}

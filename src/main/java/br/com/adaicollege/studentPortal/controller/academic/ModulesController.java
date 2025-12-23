package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academicDTO.ModulesDTO;
import br.com.adaicollege.studentPortal.service.academic.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/create-module")
public class ModulesController {

    @Autowired
    private ModulesService service;

    @PostMapping
    public ResponseEntity<ModulesDTO> create(
            @RequestBody ModulesDTO dto
    ) {
        dto.setId(null);
        ModulesDTO saved = service.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ModulesDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModulesDTO> getById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModulesDTO> update(
            @PathVariable String id,
            @RequestBody ModulesDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

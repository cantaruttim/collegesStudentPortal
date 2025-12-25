package br.com.adaicollege.studentPortal.controller.forms;

import br.com.adaicollege.studentPortal.data.formsDTO.StudentsActivityFormsDTO;
import br.com.adaicollege.studentPortal.service.forms.StudentActivityFormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student-class-activity-response")
public class StudentsActivityFormsController {

    @Autowired
    private StudentActivityFormsService service;

    @PostMapping
    public ResponseEntity<StudentsActivityFormsDTO> create(
            @RequestBody StudentsActivityFormsDTO dto
    ) {
        dto.setId(null);
        StudentsActivityFormsDTO saved = service.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<StudentsActivityFormsDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentsActivityFormsDTO> getById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentsActivityFormsDTO> update(
            @PathVariable String id,
            @RequestBody StudentsActivityFormsDTO dto
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

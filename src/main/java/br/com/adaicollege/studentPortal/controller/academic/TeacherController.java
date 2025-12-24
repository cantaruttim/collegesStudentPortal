package br.com.adaicollege.studentPortal.controller.academic;


import br.com.adaicollege.studentPortal.data.academicDTO.TeacherDTO;
import br.com.adaicollege.studentPortal.data.formsDTO.StudentsActivityFormsDTO;
import br.com.adaicollege.studentPortal.repository.academic.TeacherRepository;
import br.com.adaicollege.studentPortal.service.academic.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/create-teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @PostMapping
    public ResponseEntity<TeacherDTO> create(
            @RequestBody TeacherDTO dto
    ) {
        dto.setId(null);
        TeacherDTO saved = service.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> update(
            @PathVariable String id,
            @RequestBody TeacherDTO dto
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

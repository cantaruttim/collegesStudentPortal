package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academicDTO.CreateStudentDTO;
import br.com.adaicollege.studentPortal.service.academic.CreateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/create-student")
public class CreateStudentController {

    @Autowired
    private CreateStudentService service;

    @PostMapping
    public ResponseEntity<CreateStudentDTO> create(
            @RequestBody CreateStudentDTO dto
    ) {
        dto.setId(null);
        CreateStudentDTO saved = service.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentDTO> getById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateStudentDTO> update(
            @PathVariable String id,
            @RequestBody CreateStudentDTO dto
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

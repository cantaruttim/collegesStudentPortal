package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academic.student.CreateStudentRequest;
import br.com.adaicollege.studentPortal.data.academic.student.UpdateStudentRequest;
import br.com.adaicollege.studentPortal.data.academic.student.StudentResponse;
import br.com.adaicollege.studentPortal.service.academic.student.CreateStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class CreateStudentController {

    private final CreateStudentService service;

    public CreateStudentController(CreateStudentService service) {
        this.service = service;
    }


    @PostMapping("/create-student")
    public ResponseEntity<StudentResponse> create(
            @RequestBody CreateStudentRequest request
    ) {
        StudentResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/listing-all-students")
    public ResponseEntity<List<StudentResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/listing-one-student/{id}")
    public ResponseEntity<StudentResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PutMapping("/updating-student/{id}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable String id,
            @RequestBody UpdateStudentRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

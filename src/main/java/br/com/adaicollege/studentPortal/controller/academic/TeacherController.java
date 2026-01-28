package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherResponse;
import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.UpdateTeacherRequest;
import br.com.adaicollege.studentPortal.service.academic.secretary.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PostMapping
    public ResponseEntity<TeacherResponse> create(
            @RequestBody TeacherRequest request
    ) {
        TeacherResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY')")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(
            @PathVariable String id,
            @RequestBody UpdateTeacherRequest request
    ) {
        TeacherResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }


}

package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherResponse;
import br.com.adaicollege.studentPortal.service.academic.secretary.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @PostMapping
    public ResponseEntity<TeacherResponse> create(
            @RequestBody TeacherRequest request
    ) {
        TeacherResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

}

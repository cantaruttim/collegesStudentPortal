package br.com.adaicollege.studentPortal.controller.forms;

import br.com.adaicollege.studentPortal.data.activities.forms.ActivityFormsResponse;
import br.com.adaicollege.studentPortal.data.activities.forms.StudentsActivityFormRequest;
import br.com.adaicollege.studentPortal.service.forms.StudentActivityFormsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student-class-activity-response")
public class StudentsActivityFormsController {

    private StudentActivityFormsService service;

    public StudentsActivityFormsController(StudentActivityFormsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ActivityFormsResponse> create(
            @RequestBody StudentsActivityFormRequest request
    ) {
        ActivityFormsResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ActivityFormsResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityFormsResponse> getById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

}

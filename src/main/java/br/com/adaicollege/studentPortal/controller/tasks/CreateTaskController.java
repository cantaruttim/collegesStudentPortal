package br.com.adaicollege.studentPortal.controller.tasks;

import br.com.adaicollege.studentPortal.data.activities.tasks.CreateTasksRequest;
import br.com.adaicollege.studentPortal.data.activities.tasks.CreateTasksResponse;
import br.com.adaicollege.studentPortal.service.tasks.CreateTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/create-task")
public class CreateTaskController {

    // Students must just read tasks

    private final CreateTaskService service;

    public CreateTaskController(CreateTaskService service) {
        this.service = service;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'TEACHER')")
    @PostMapping("/create-task")
    public ResponseEntity<CreateTasksResponse> create(
            @RequestBody CreateTasksRequest request
    ) {
        CreateTasksResponse saved = service.create(request);
        return ResponseEntity.status(201).body(saved);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'TEACHER')")
    @GetMapping("/listing-all-tasks")
    public ResponseEntity<List<CreateTasksResponse>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'TEACHER')")
    @GetMapping("/listing-one-task/{id}")
    public ResponseEntity<CreateTasksResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("""
        hasAnyRole('ADMIN', 'TEACHER', 'SECRETARY')
    """)
    @PutMapping("/updating-task/{id}")
    public ResponseEntity<CreateTasksResponse> update(
            @PathVariable String id,
            @RequestBody CreateTasksRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SECRETARY', 'TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}

package br.com.adaicollege.studentPortal.controller.auth;

import br.com.adaicollege.studentPortal.data.auth.FirstAccessRequest;
import br.com.adaicollege.studentPortal.data.auth.FirstAccessResponse;
import br.com.adaicollege.studentPortal.model.academic.student.CreateStudent;
import br.com.adaicollege.studentPortal.repository.academic.CreateStudentRepository;
import br.com.adaicollege.studentPortal.service.academic.student.FirstAccessService;
import br.com.adaicollege.studentPortal.service.academic.student.StudentLoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class StudentFirstAccessController {

    private final FirstAccessService firstAccessService;
    private final StudentLoginService studentLoginService;
    private final CreateStudentRepository studentRepository;

    public StudentFirstAccessController(FirstAccessService firstAccessService,
                                        StudentLoginService studentLoginService,
                                        CreateStudentRepository studentRepository) {
        this.firstAccessService = firstAccessService;
        this.studentLoginService = studentLoginService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/first-access")
    public ResponseEntity<FirstAccessResponse> firstAccess(
            @Valid @RequestBody FirstAccessRequest request
    ) {
        firstAccessService.firstAccess(
                request.registrationNumber(),
                request.currentPassword(),
                request.newPassword()
        );

        FirstAccessResponse response = new FirstAccessResponse(
                request.registrationNumber(),
                "Password changed successfully!"
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping("/first-access/resend/{studentId}")
    public ResponseEntity<Void> resendFirstAccessPassword(
            @PathVariable String studentId
    ) {

        CreateStudent student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentLoginService.resendFirstAccessPassword(student);

        return ResponseEntity.noContent().build();
    }
}

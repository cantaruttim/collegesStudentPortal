package br.com.adaicollege.studentPortal.controller.forms;


import br.com.adaicollege.studentPortal.repository.forms.StudentActivityFormsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student-activity-response")
public class StudentsActivityFormsController {

    @Autowired
    private StudentActivityFormsRepository repo;

}

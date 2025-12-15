package br.com.adaicollege.studentPortal.controller.academic;


import br.com.adaicollege.studentPortal.repository.academic.CreateStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/create-student")
public class CreateStudentController {

    @Autowired
    private CreateStudentRepository repo;

}

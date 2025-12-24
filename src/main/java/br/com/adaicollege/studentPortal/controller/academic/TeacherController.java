package br.com.adaicollege.studentPortal.controller.academic;


import br.com.adaicollege.studentPortal.repository.academic.TeacherRepository;
import br.com.adaicollege.studentPortal.service.academic.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/create-teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

}

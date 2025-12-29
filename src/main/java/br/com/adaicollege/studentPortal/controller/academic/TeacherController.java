package br.com.adaicollege.studentPortal.controller.academic;


import br.com.adaicollege.studentPortal.service.academic.secretary.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;


}

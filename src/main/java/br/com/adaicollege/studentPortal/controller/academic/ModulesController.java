package br.com.adaicollege.studentPortal.controller.academic;

import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesDTO;
import br.com.adaicollege.studentPortal.service.academic.secretary.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/create-module")
public class ModulesController {

    @Autowired
    private ModulesService service;


}

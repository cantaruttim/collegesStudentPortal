package br.com.adaicollege.studentPortal.service.academic.secretary;

import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesResponse;
import br.com.adaicollege.studentPortal.data.activities.ActivityFormsResponse;
import br.com.adaicollege.studentPortal.repository.academic.ModulesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModulesService {

    private final ModulesRepository repo;

    public ModulesService(ModulesRepository repo) {
        this.repo = repo;
    }

    public List<ModulesResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(ModulesResponse::new)
                .toList();
    }


}

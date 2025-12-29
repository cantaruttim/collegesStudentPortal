package br.com.adaicollege.studentPortal.service.academic.secretary;

import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesResponse;
import br.com.adaicollege.studentPortal.model.academic.secretary.Modules;
import br.com.adaicollege.studentPortal.repository.academic.ModulesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ModulesService {

    private final ModulesRepository repo;

    public ModulesService(ModulesRepository repo) {
        this.repo = repo;
    }





    public ModulesResponse create(ModulesRequest request) {
        Modules forms = Modules.from(request);
        Modules saved = repo.save(forms);

        return new ModulesResponse(saved);
    }

    public ModulesResponse findById(String id) {
        return new ModulesResponse(
                repo.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        ))
        );
    }

    public List<ModulesResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(ModulesResponse::new)
                .toList();
    }


}

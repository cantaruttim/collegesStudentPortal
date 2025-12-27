package br.com.adaicollege.studentPortal.service.forms;

import br.com.adaicollege.studentPortal.data.forms.activities.ActivityFormsResponse;
import br.com.adaicollege.studentPortal.data.forms.activities.StudentsActivityFormRequest;
import br.com.adaicollege.studentPortal.model.forms.activities.StudentsActivityForms;
import br.com.adaicollege.studentPortal.repository.forms.StudentActivityFormsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentActivityFormsService {

    private final StudentActivityFormsRepository repo;

    public StudentActivityFormsService(StudentActivityFormsRepository repo) {
        this.repo = repo;
    }

    public ActivityFormsResponse create(StudentsActivityFormRequest request) {
        // implements a warning if the response were sand at that specific day

        StudentsActivityForms forms = StudentsActivityForms.from(request);

        StudentsActivityForms saved = repo.save(forms);

        return new ActivityFormsResponse(saved);
    }

    public List<ActivityFormsResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(ActivityFormsResponse::new)
                .toList();
    }


    public ActivityFormsResponse findById(String id) {
        return new ActivityFormsResponse(
                repo.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        ))
        );
    }




}

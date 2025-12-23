package br.com.adaicollege.studentPortal.repository.forms;

import br.com.adaicollege.studentPortal.model.forms.StudentsActivityForms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentActivityFormsRepository
        extends MongoRepository<
            StudentsActivityForms,
            String
        > {
}

package br.com.adaicollege.studentPortal.repository.academic;

import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateStudentRepository
        extends MongoRepository<
            CreateStudent,
            String
        > {

    boolean existsBySocialSecurityNumber(String socialSecurityNumber);

    boolean existsByEmail(String email);

}

package br.com.adaicollege.studentPortal.repository.academic;


import br.com.adaicollege.studentPortal.model.academic.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository
        extends MongoRepository<
            Teacher,
            String
        > {
}

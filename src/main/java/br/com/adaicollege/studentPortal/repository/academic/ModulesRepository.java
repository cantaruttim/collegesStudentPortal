package br.com.adaicollege.studentPortal.repository.academic;


import br.com.adaicollege.studentPortal.model.academic.Modules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulesRepository
        extends MongoRepository<
            Modules,
            String
        > {
}

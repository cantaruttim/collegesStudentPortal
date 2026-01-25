package br.com.adaicollege.studentPortal.repository.tasks;

import br.com.adaicollege.studentPortal.model.forms.tasks.CreateTasks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreateTaskRepository
        extends MongoRepository<
            CreateTasks,
            String
        > {



}

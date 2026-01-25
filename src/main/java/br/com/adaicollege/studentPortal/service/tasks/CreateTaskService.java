package br.com.adaicollege.studentPortal.service.tasks;

import br.com.adaicollege.studentPortal.config.exceptions.ModuleNotFoundException;
import br.com.adaicollege.studentPortal.data.activities.tasks.CreateTasksRequest;
import br.com.adaicollege.studentPortal.data.activities.tasks.CreateTasksResponse;
import br.com.adaicollege.studentPortal.model.forms.tasks.CreateTasks;
import br.com.adaicollege.studentPortal.repository.tasks.CreateTaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class CreateTaskService {

    private final CreateTaskRepository repo;

    public CreateTaskService(CreateTaskRepository repo) {
        this.repo = repo;
    }



    public CreateTasksResponse create(CreateTasksResponse request) {
        CreateTasks task = CreateTasks.from(request);
        return new CreateTasksResponse(task);
    }

    public CreateTasksResponse findById(String id) {
        return new CreateTasksResponse(
                repo.findById(id).orElseThrow(() -> new ModuleNotFoundException(id))
        );
    }

    public List<CreateTasksResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(CreateTasksResponse::new)
                .toList();
    }

    public CreateTasksResponse update(String id, CreateTasksRequest request) {


        CreateTasks task = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task not found: " + id
                ));

        task.setTitle(request.titleTask());
        task.setSubtitle(request.subtitle());
        task.setDescription(request.description());
        task.setModuleId(request.moduleId());
        task.setTeacherId(request.teacherId());
        task.setCreateAt(request.createAt());
        task.setExpireAt(request.expireAt());
        task.setResponseBy(request.responseBy());

        return new CreateTasksResponse(repo.save(task));
    }



}

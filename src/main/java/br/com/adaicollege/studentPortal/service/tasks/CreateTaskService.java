package br.com.adaicollege.studentPortal.service.tasks;

import br.com.adaicollege.studentPortal.config.exceptions.TaskNotFoundException;
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


    public CreateTasksResponse create(CreateTasksRequest request) {
        CreateTasks task = CreateTasks.from(request);
        CreateTasks saved = repo.save(task);
        return new CreateTasksResponse(saved);
    }

    public CreateTasksResponse findById(String id) {
        return new CreateTasksResponse(
                repo.findById(id).orElseThrow(TaskNotFoundException::new)
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


    public void delete(String id) {
        var task = repo.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        repo.delete(task);
    }




}

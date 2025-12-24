package br.com.adaicollege.studentPortal.service.academic;

import br.com.adaicollege.studentPortal.config.mapper.academic.CreateStudentMapper;
import br.com.adaicollege.studentPortal.data.academicDTO.CreateStudentDTO;
import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import br.com.adaicollege.studentPortal.repository.academic.CreateStudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreateStudentService {

    private final CreateStudentRepository repo;

    public CreateStudentService(CreateStudentRepository repo) {
        this.repo = repo;
    }


    // -------------------------------------------------------------
    // CREATE
    // -------------------------------------------------------------
    public CreateStudentDTO save(CreateStudentDTO dto) {

        CreateStudent student = CreateStudentMapper.toEntity(dto);
        student.setId(null); // Mongo gera o ID

        CreateStudent saved = repo.save(student);
        return CreateStudentMapper.toDTO(saved);
    }

    // -------------------------------------------------------------
    // LIST ALL
    // -------------------------------------------------------------
    public List<CreateStudentDTO> listAll() {
        return repo.findAll()
                .stream()
                .map(CreateStudentMapper::toDTO)
                .toList();
    }

    // -------------------------------------------------------------
    // FIND BY ID
    // -------------------------------------------------------------
    public CreateStudentDTO findById(String id) {

        CreateStudent mod = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        return CreateStudentMapper.toDTO(mod);
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    public CreateStudentDTO update(String id, CreateStudentDTO dto) {

        CreateStudent student = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        student.setFirstName(dto.getFirstName());
        // IMPLEMENTS!

        CreateStudent updated = repo.save(student);
        return CreateStudentMapper.toDTO(updated);
    }

    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    public void delete(String id) {

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found: " + id
            );
        }

        repo.deleteById(id);
    }


}

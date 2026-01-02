package br.com.adaicollege.studentPortal.auth.model;


import br.com.adaicollege.studentPortal.auth.RoleName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="roles")
public class Role {

    @Id
    private Integer id;

    private RoleName roleName;

}

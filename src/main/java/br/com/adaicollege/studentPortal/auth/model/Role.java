package br.com.adaicollege.studentPortal.auth.model;


import br.com.adaicollege.studentPortal.auth.RoleName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection="roles")
public class Role {

    @Id
    private Integer id;

    private RoleName roleName;
    private Set<String> permissions = new HashSet<>();

}

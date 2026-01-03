package br.com.adaicollege.studentPortal.auth.model;

import br.com.adaicollege.studentPortal.auth.enums.Permission;
import br.com.adaicollege.studentPortal.auth.enums.RoleName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection="roles")
public class Role {

    @Id
    private String id;

    private RoleName roleName;
    private Set<Permission> permissions = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

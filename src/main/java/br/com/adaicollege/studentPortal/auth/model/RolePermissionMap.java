package br.com.adaicollege.studentPortal.auth.model;

import br.com.adaicollege.studentPortal.auth.enums.Permission;
import br.com.adaicollege.studentPortal.auth.enums.RoleName;

import java.util.EnumMap;
import java.util.Set;

public class RolePermissionMap {

    public static final EnumMap<RoleName, Set<Permission>> MAP = new EnumMap<>(RoleName.class);

    static {
        MAP.put(RoleName.ADMIN, Set.of(
           Permission.STUDENT_CREATE,
           Permission.STUDENT_READ,
           Permission.STUDENT_UPDATE,
           Permission.STUDENT_DELETE,
           Permission.TEACHER_CREATE,
           Permission.TEACHER_READ,
           Permission.TEACHER_UPDATE,
           Permission.TEACHER_DELETE

        ));

        MAP.put(RoleName.SECRETARY, Set.of(
            Permission.STUDENT_CREATE,
            Permission.STUDENT_READ,
            Permission.STUDENT_UPDATE,
            Permission.TEACHER_CREATE,
            Permission.TEACHER_READ,
            Permission.TEACHER_UPDATE,
            Permission.TEACHER_DELETE
        ));

        MAP.put(RoleName.STUDENT, Set.of(
            Permission.STUDENT_READ_SELF,
            Permission.STUDENT_UPDATE_SELF
        ));

        MAP.put(RoleName.TEACHER, Set.of(
                Permission.TEACHER_READ_SELF,
                Permission.TEACHER_UPDATE_SELF
        ));

    }

    private RolePermissionMap() {}


}

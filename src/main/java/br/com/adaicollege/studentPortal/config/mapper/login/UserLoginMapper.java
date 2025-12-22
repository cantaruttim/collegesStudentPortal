package br.com.adaicollege.studentPortal.config.mapper.login;

import br.com.adaicollege.studentPortal.data.loginDTO.UserLoginDTO;
import br.com.adaicollege.studentPortal.model.login.UserLogin;

public class UserLoginMapper {

    // ======================================================
    // DTO → Entity
    // ======================================================
    public static UserLogin toEntity(UserLoginDTO dto) {

        if (dto == null ) return null;
        UserLogin user = new UserLogin();

        user.setRegistrationNumber(dto.getRegistrationNumber());
        user.setStudentPassword(dto.getStudentPassword());

        return user;
    }

    // ======================================================
    // Entity → DTO
    // ======================================================
    public static UserLoginDTO toDTO(UserLogin user) {

        if (user == null) return null;
        UserLoginDTO dto = new UserLoginDTO();

        dto.setRegistrationNumber(user.getRegistrationNumber());
        dto.setStudentPassword(user.getStudentPassword());
        return dto;
    }
}

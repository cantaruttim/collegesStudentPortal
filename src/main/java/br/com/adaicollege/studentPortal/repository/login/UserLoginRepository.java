package br.com.adaicollege.studentPortal.repository.login;


import br.com.adaicollege.studentPortal.model.login.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String> {

    Optional<UserLogin> findByRegistrationNumber(String registrationNumber);
}

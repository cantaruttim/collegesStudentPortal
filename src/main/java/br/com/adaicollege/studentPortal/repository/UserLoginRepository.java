package br.com.adaicollege.studentPortal.repository;


import br.com.adaicollege.studentPortal.model.auth.UserLogin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends MongoRepository<UserLogin, String> {

}

package com.liberty.soge.repository;

import com.liberty.soge.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Repository
public interface UserRepository extends MongoRepository<UserAccount, String> {

}

package com.streamslience.springdatamongo.mongodbdatadatasource.repositories.secondary;

import com.streamslience.springdatamongo.mongodbdatadatasource.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author StreamSlience
 * @date 2020-07-09 22:08
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}

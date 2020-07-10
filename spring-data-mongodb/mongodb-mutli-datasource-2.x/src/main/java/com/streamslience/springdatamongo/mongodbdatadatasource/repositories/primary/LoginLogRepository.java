package com.streamslience.springdatamongo.mongodbdatadatasource.repositories.primary;


import com.streamslience.springdatamongo.mongodbdatadatasource.entity.LoginLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author StreamSlience
 * @date 2020-07-09 22:30
 */
@Repository
public interface LoginLogRepository extends MongoRepository<LoginLog,String> {
}

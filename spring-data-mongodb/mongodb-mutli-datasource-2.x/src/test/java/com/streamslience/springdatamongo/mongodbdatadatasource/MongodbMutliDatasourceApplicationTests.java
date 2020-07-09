package com.streamslience.springdatamongo.mongodbdatadatasource;

import com.streamslience.springdatamongo.mongodbdatadatasource.entity.LoginLog;
import com.streamslience.springdatamongo.mongodbdatadatasource.entity.User;
import com.streamslience.springdatamongo.mongodbdatadatasource.repositories.primary.LoginLogRepository;
import com.streamslience.springdatamongo.mongodbdatadatasource.repositories.secondary.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbMutliDatasourceApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginLogRepository loginLogRepository;

    @Test
    public void inset() {
        User user = User.builder().id(UUID.randomUUID() + "").username("小明").registerTime(Instant.now()).build();
        userRepository.insert(user);

        LoginLog login = LoginLog.builder().id(UUID.randomUUID() + "").uid(user.getId()).loginTime(Instant.now()).username("小明").build();
        loginLogRepository.insert(login);
    }

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;
    @Autowired
    @Qualifier("secondaryMongoTemplate")
    private MongoTemplate secondaryMongoTemplate;

    @Test
    void save() {
        User user = User.builder().id(UUID.randomUUID() + "").username("小明").registerTime(Instant.now()).build();
        secondaryMongoTemplate.save(user);

        LoginLog login = LoginLog.builder().id(UUID.randomUUID() + "").uid(user.getId()).loginTime(Instant.now()).username("小明").build();
        primaryMongoTemplate.save(login);
    }
}

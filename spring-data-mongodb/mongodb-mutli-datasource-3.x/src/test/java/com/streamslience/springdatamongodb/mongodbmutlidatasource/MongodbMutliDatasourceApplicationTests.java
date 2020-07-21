package com.streamslience.springdatamongodb.mongodbmutlidatasource;

import com.streamslience.springdatamongodb.mongodbmutlidatasource.entity.LoginLog;
import com.streamslience.springdatamongodb.mongodbmutlidatasource.entity.User;
import com.streamslience.springdatamongodb.mongodbmutlidatasource.repositories.primary.LoginLogRepository;
import com.streamslience.springdatamongodb.mongodbmutlidatasource.repositories.secondary.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbMutliDatasourceApplicationTests {

    @Test
    public void contextLoads() {
    }

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
    @Qualifier("primaryMongoTemplate_1")
    private MongoTemplate primaryMongoTemplate;
    @Autowired
    @Qualifier("secondaryMongoTemplate_1")
    private MongoTemplate secondaryMongoTemplate;

    @Test
    void save() {
        User user = User.builder().id(UUID.randomUUID() + "").username("小明").registerTime(Instant.now()).build();
        secondaryMongoTemplate.save(user);

        LoginLog login = LoginLog.builder().id(UUID.randomUUID() + "").uid(user.getId()).loginTime(Instant.now()).username("小明").build();
        primaryMongoTemplate.save(login);
    }

}

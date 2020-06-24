package com.streamslience.springdatajpa.multitablequery;

import com.streamslience.springdatajpa.multitablequery.dao.IPersonDao;
import com.streamslience.springdatajpa.multitablequery.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author StreamSlience
 * @date 2020-06-16 0:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MultitablequeryTest {

    @Autowired
    private IPersonDao iPersonDao;

    @Test
    @Sql(scripts = {"classpath:/init.sql"})
    public void getUserInformation() {
        Optional<UserDTO> result = iPersonDao.getUserInformation(1L);
        UserDTO dto = result.get();
        Assert.assertEquals("小蓝",dto.getName());
    }


    @Test
    @Sql(scripts = {"classpath:/init.sql"})
    public void findPersonAgeOlderThan18() {
        List<UserDTO> result = iPersonDao.filterUserInfoByAge(18, 99);
        Assert.assertEquals(4, result.size());
        result.forEach(System.out::println);
    }

    @Test
    @Sql(scripts = {"classpath:/init.sql"})
    public void getUserInformationList() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "age");
        Page<UserDTO> userInformationList = iPersonDao.getUserInformationList(pageRequest);
        System.out.println(userInformationList.getTotalElements());
        System.out.println(userInformationList.getTotalPages());
        System.out.println(userInformationList.getContent());
        userInformationList.getContent().forEach(System.out::println);
    }

    @Test
    @Sql(scripts = {"classpath:/init.sql"})
    public void getUserInformationListByAgeGreaterThan() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "age");
        Page<UserDTO> userInformationList = iPersonDao.getUserInformationListByAgeGreaterThan(pageRequest, 20);
        System.out.println(userInformationList.getTotalElements());
        System.out.println(userInformationList.getTotalPages());
        System.out.println(userInformationList.getContent());
        userInformationList.getContent().forEach(System.out::println);
    }

    @Test
    @Sql(scripts = {"classpath:/init.sql"})
    public void filterUserInfo() {
        List<String> personList = new ArrayList<>(Arrays.asList("person1", "person2"));
        List<UserDTO> userDTOS = iPersonDao.filterUserInfo(personList);
        Assert.assertEquals(0, userDTOS.size());

        personList = new ArrayList<>(Arrays.asList("小白", "小黑"));
        userDTOS = iPersonDao.filterUserInfo(personList);
        Assert.assertEquals(2, userDTOS.size());
    }
}





















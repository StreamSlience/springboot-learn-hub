package com.streamslience.springdatajpa.crud;

import com.streamslience.springdatajpa.crud.dao.IRoleDao;
import com.streamslience.springdatajpa.crud.entity.Gender;
import com.streamslience.springdatajpa.crud.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author StreamSlience
 * @date 2020-06-14 17:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaCrudTest {

    @Autowired
    private IRoleDao iRoleDao;

    /**
     * 保存数据到数据库
     */
    @Test
    public void saveRole() {
        Role role = new Role()
                .setAge(8)
                .setDescription("小红帽戴着小红帽去找外婆。")
                .setGender(Gender.FEMALE)
                .setName("小红帽");
        System.out.println(role);
        Role role1 = iRoleDao.save(role);
        System.out.println(role1);
    }

    /**
     * 根据主键查询数据
     */
    @Test
    public void selectById() {
        Optional<Role> role = iRoleDao.findById(14L);
        System.out.println(role);
    }

    /**
     * 根据主键删除
     */
    @Test
    public void deleteById() {
        iRoleDao.deleteById(14L);
        Optional<Role> role = iRoleDao.findById(14L);
        System.out.println(role);
    }

    /**
     * 修改操作也是通过save方法，实际上是通过主键作为查询条件的
     */
    @Test
    public void updateById() {
        Role role = new Role()
                .setName("old");
        iRoleDao.save(role);
        System.err.println(role);
        role.setName("new");
        iRoleDao.save(role);
        System.err.println(role);
    }

    /**
     * 查询所有数据
     */
    @Test
    public void findAll() {
        List<Role> roles = iRoleDao.findAll();
        System.err.println(roles);
    }

    /**
     * 根据主键集合进行查询
     */
    @Test
    public void fiandAllById() {
        List<Role> roles = iRoleDao.findAllById(Arrays.asList(0L, 15L, 16L, 17L));
        roles.stream().forEach(System.out::println);
    }

    /**
     * 查询所有数据并进行排序
     */
    @Test
    public void findAllSorted() {
        //Sort sort = new Sort(Sort.Direction.DESC, Arrays.asList("id"));
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Role> roles = iRoleDao.findAll(sort);
        roles.forEach(System.out::println);
    }

    @Test
    public void saveAll() {
        List<Role> roles = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> {
            Role role = new Role();
            role.setName(new Random().nextInt(100) + "")
                    .setGender(Gender.FEMALE)
                    .setAge(new Random().nextInt(10));
            roles.add(role);
        });
        List<Role> roleEntities = iRoleDao.saveAll(roles);
        roleEntities.forEach(System.out::println);
    }

    @Test
    public void saveAndFlush() {

    }

    @Test
    public void deleteInBatch() {
        this.saveAll();
        List<Role> roles = iRoleDao.findAll();
        roles.get(0).setId(0L);//可以设置
        roles.get(1).setId(null);//抛出异常
        roles.forEach(System.out::println);
        iRoleDao.deleteInBatch(roles);
        roles = iRoleDao.findAll();
        roles.forEach(System.out::println);
    }

    /**
     * 批量删除所有数据
     */
    @Test
    public void deleteAllInBatch() {
        iRoleDao.deleteAllInBatch();
    }

    /**
     * 异常：org.hibernate.LazyInitializationException:
     * could not initialize proxy
     * [com.example.springlearn.annotation.jpa.createtableentityobjects.Role#56] - no Session
     * 解决方法：在出问题的实体类上加@Proxy(lazy = false)，因为hibernate默认是懒加载的。
     */
    @Test
    public void getOne() {
        Role role = new Role().setName("哈哈哈哈哈哈");
        Role roleEntities = iRoleDao.save(role);
        System.err.println(roleEntities);
        iRoleDao.flush();
        role = iRoleDao.getOne(roleEntities.getId());
        System.err.println(role);
    }

    @Test
    public void findAllExample() {
        //Example example = new Example().enableLike();
        Role role = new Role().setId(61L);
        Example<Role> example = Example.of(role);
        List<Role> list = iRoleDao.findAll(example);
        System.err.println(list);
    }

}

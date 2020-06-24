package com.streamslience.springdatajpa.crud;

import com.streamslience.springdatajpa.crud.dao.IRoleDao;
import com.streamslience.springdatajpa.crud.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author StreamSlience
 * @date 2020-06-14 18:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaWrapperTest {

    @Autowired
    private IRoleDao iRoleDao;

    /**
     * 只适用于数据库字段值是唯一的场景下。
     * 如果指定的查询字段不唯一(即表中存在多行有相同的值)，会抛出异常。
     * 会抛出异常：
     * org.springframework.dao.IncorrectResultSizeDataAccessException: query did not return a unique result: 2;
     * nested exception is javax.persistence.NonUniqueResultException: query did not return a unique result: 2
     * <p>
     * 如果指定的字段值在表中不存在，不会抛出异常。
     * 但是在获取值得时候需要注意，不能使用 {@code Optional#get}方法，会抛出异常：
     * java.util.NoSuchElementException: No value present
     * 应该使用{@code Optional#orElse}方法或 {@code Optional#orElseGet}方法
     * 这两个方法的功能是等效的，只是{@code Optional#orElseGet}方法入参是方法引用。
     */
    @Test
    public void findByName() {
        Optional<Role> roles = iRoleDao.findByName("大红帽");
        //System.err.println(roles.orElseGet(Role::new));
        //System.err.println(roles.orElse(new Role()));
        //System.err.println(roles.get());
    }

    /**
     * select
     * count(role0_.id) as col_0_0_
     * from
     * role role0_
     * where
     * role0_.age between ? and ?
     */
    @Test
    public void countByAgeBetween() {
        int count = iRoleDao.countByAgeBetween(1, 4);
        System.err.println(count);
    }

    /**
     * 跟上面的findByName等效
     */
    @Test
    public void findByNameCustomeQuery() {
        Optional<Role> role = iRoleDao.findByNameCustomeQuery("大红帽");
        System.err.println(role.orElseGet(Role::new));
    }

    /**
     *
     */
    @Test
    public void findRoleByIdReturnName() {
        String name = iRoleDao.findRoleByIdReturnName(17L);
        System.err.println(name);
    }

    /**
     * 实际上举例updateRoleAgeById 和 updateRoleNameById 这两个方法的目的在于说明JAP框架中
     * 是入库将参数插入到sql中的
     * <p>
     * 第一种情况：
     *
     * @Query("update Role p set p.name = ?1 where p.id = ?2")
     * void updateRoleNameById(String name, Long id);
     * 实际上方法签名中的每个参数JAP框架都会按照从左到右从1开始递增分配唯一标识，在自定义sql中可以通过 ? 加上 序号 进行占位
     * 在这种情况下不能使用 : 加 字段名 进行占位  会导致异常 java.lang.IllegalStateException: Failed to load ApplicationContext
     *
     * <p>
     * 第二种情况：
     * @Query("update Role p set p.age = :age where p.id = :id")
     * void updateRoleAgeById(@Param("age") Integer age, @Param("id") Long id);
     * 我们可以使用@Param注解为方法的每个入参指定别名，在自定义sql中可以通过 : 叫上 别名 进行占位。
     * 当然这种情况下同样可以使用 ? 加上 序号 进行占位.
     * 需要注意的是 :别名 和 ?序号 这两种形式不能同时出现在一句sql里面 会导致启动异常
     */
    @Test
    public void updateRole() {

        Optional<Role> role = iRoleDao.findById(17L);
        System.err.println(role.orElseGet(Role::new));

        iRoleDao.updateRoleAgeById(18, 17L);
        role = iRoleDao.findById(17L);
        System.err.println(role.orElseGet(Role::new));

        iRoleDao.updateRoleNameById("大红帽" , 17L);
        role = iRoleDao.findById(17L);
        System.err.println(role.orElseGet(Role::new));

    }
}
































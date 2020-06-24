package com.streamslience.springdatajpa.crud.dao;

import com.streamslience.springdatajpa.crud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;


/**
 * 持久层接口
 *
 * @author StreamSlience
 * @date 2020-06-14 17:51
 */
@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {

    /**
     * 自定义.根据名称查询
     *
     * @param name 名字
     * @return Role实体
     */
    Optional<Role> findByName(String name);

    /**
     * 自定义.查询年龄大于某个值得人
     *
     * @param age 年龄
     * @return Role实体集合
     */
    List<Role> findByAgeGreaterThan(int age);

    /**
     * 查询年龄在指定范围内的人的数量
     *
     * @param left 年龄下限(包含当前年龄)
     * @param right 年龄上限(包含当前年龄)
     * @return 符合年龄范围的人数
     */
    int countByAgeBetween(int left, int right);

    /**
     * 根据名称查询全部数据
     *
     * @param name 名字
     * @return Role实体
     */
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByNameCustomeQuery(@Param("name") String name);

    /**
     * 部分属性查询，避免 select *操作
     *
     * @param id 主键
     * @return 名字
     */
    @Query("SELECT r.name FROM Role r WHERE r.id = :id")
    String findRoleByIdReturnName(@Param("id") Long id);

    /**
     * 根据名称查询
     *
     * @param name 名字
     * @return 返回Role实体集合
     */
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    List<Role> findsByNameCustomeQuery(@Param("name") String name);

    /**
     * 修改名称
     *
     * @param name 名字
     * @param id 主键
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Role p set p.name = ?1 where p.id = ?2")
    void updateRoleNameById(String name, Long id);

    /**
     * 修改年龄
     *
     * @param age 年龄
     * @param id  主键
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update Role p set p.age = ?1 where p.id = ?2")
    void updateRoleAgeById(@Param("age") Integer age, @Param("id") Long id);

    /**
     * 异步方法
     *
     * @return 异步返回Role实体集合
     */
    @Async
    @Query("SELECT r  FROM  Role r ")
    Future<List<Role>> findAllAsync();

}

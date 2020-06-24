package com.streamslience.springdatajpa.multitablequery.dao;

import com.streamslience.springdatajpa.multitablequery.dto.UserDTO;
import com.streamslience.springdatajpa.multitablequery.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 持久层接口
 *
 * @author StreamSlience
 * @date 2020-06-15 22:57
 */
@Repository
public interface IPersonDao extends JpaRepository<Person, Long> {

    /**
     * 根据主键查询对应关联信息
     *
     * @param personId 人员主键
     * @return 查询实体UserDTO
     */
    @Query(value = "SELECT new com.streamslience.springdatajpa.multitablequery.dto.UserDTO(p.name, p.age, c.companyName, s.name)\n" +
            "FROM Person p LEFT JOIN Company c ON p.id=c.id\n" +
            "LEFT JOIN School s ON p.schoolId = s.id\n" +
            "WHERE p.id = :personId")
    Optional<UserDTO> getUserInformation(@Param("personId") Long personId);

    /**
     * 无条件分页查询
     *
     * @param pageable 分页对象
     * @return 查询实体UserDTO
     */
    @Query(value = "SELECT new com.streamslience.springdatajpa.multitablequery.dto.UserDTO(p.name, p.age, c.companyName, s.name)\n" +
            "FROM Person p LEFT JOIN Company  c ON p.companyId=c.id\n" +
            "LEFT JOIN School s ON p.schoolId = s.id",
            countQuery = "SELECT COUNT(p.id) FROM Person p\n" +
                    "LEFT JOIN Company c ON p.companyId = c.id\n" +
                    "LEFT JOIN School s ON p.schoolId = s.id")
    Page<UserDTO> getUserInformationList(Pageable pageable);

    /**
     * 查询年龄大于指定只的关联信息并分页
     *
     * @param pageable 分页对象
     * @param age 年龄
     * @return 查询实体UserDTO分页对象
     */
    @Query(value = "SELECT new com.streamslience.springdatajpa.multitablequery.dto.UserDTO(p.name, p.age, c.companyName, s.name)\n" +
            "FROM Person p LEFT JOIN Company c ON p.companyId=c.id\n" +
            "LEFT JOIN School s ON p.schoolId = s.id\n" +
            "WHERE p.age > :age",
            countQuery = "SELECT COUNT(p.id) FROM Person p\n" +
                    "LEFT JOIN Company c ON p.companyId = c.id\n" +
                    "LEFT JOIN School s ON p.schoolId = s.id")
    Page<UserDTO> getUserInformationListByAgeGreaterThan(Pageable pageable, int age);

    /**
     * IN 使用
     *
     * @param peopleList 人员信息集合
     * @return 查询实体UserDTO集合
     */
    @Query(value = "SELECT new com.streamslience.springdatajpa.multitablequery.dto.UserDTO(p.name, p.age, c.companyName, s.name)\n" +
            "FROM Person p LEFT JOIN Company c ON p.companyId=c.id\n" +
            "LEFT JOIN School s ON p.schoolId = s.id\n" +
            "WHERE p.name IN ?1")
    List<UserDTO> filterUserInfo(List<String> peopleList);

    /**
     * BETWEEN 使用
     *
     * @param small 年龄下限
     * @param big 年龄上限
     * @return 查询实体UserDTO集合
     */
    @Query(value = "SELECT new com.streamslience.springdatajpa.multitablequery.dto.UserDTO(p.name, p.age, c.companyName, s.name)\n" +
            "FROM Person p LEFT JOIN Company c ON p.companyId=c.id\n" +
            "LEFT JOIN School s ON p.schoolId = s.id\n" +
            "WHERE p.age BETWEEN ?1 AND ?2")
    List<UserDTO> filterUserInfoByAge(int small, int big);
}


























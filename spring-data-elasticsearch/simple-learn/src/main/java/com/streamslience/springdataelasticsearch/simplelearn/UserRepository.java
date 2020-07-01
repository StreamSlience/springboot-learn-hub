package com.streamslience.springdataelasticsearch.simplelearn;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-06-30 1:16
 */
public interface UserRepository extends ElasticsearchRepository<SysUser,Long> {

    /**
     * 根据昵称查找用户
     * @param nickName
     * @return
     */
    List<SysUser> findByNickname(String nickName);

    /**
     * 根据昵称或者用户名进行查找
     * @param nickName
     * @param Password
     * @return
     */
    List<SysUser> findByNicknameOrPassword(String nickName,String Password);

}

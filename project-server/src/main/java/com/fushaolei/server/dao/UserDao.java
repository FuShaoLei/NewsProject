package com.fushaolei.server.dao;

import com.fushaolei.server.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUser(@Param("username") String username, @Param("password") String password);

    int updateUser(@Param("id") int id, @Param("name") String name, @Param("sign") String sign);

    User getUserById(@Param("id") int id);

    User getUserByUsername(@Param("username") String username);

    int inserUser(User user);

    int updateUserAvator(@Param("id") int id, @Param("avator") String avator);
}

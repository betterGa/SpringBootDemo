package com.jia.SpringBootDemo.dao;

import com.jia.SpringBootDemo.entry.User;
import com.jia.SpringBootDemo.entry.personEntry;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {

    @Results({@Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "sex", column = "gender")})

    @Select("select * from person")
    List<personEntry> list();


    @Select("select * from person where name like #{username}")
    public List<personEntry> findByName(String name);

    @Select("select * from t_user where username like #{username}")
    List<User> findByUsername(String username);

    @Select("select * from t_user where user_id like #{userId}")
    User getOne(String userId);

    @Delete("delete from t_user where user_id like #{userId}")
    int delete(String userId);

    @Update("update person set name=#{name} where id = #{id}")
    int update(String id, String name);

    @SelectProvider(type = UserSqlProvider.class, method = "getBadUser")
    public List<personEntry> getBadUser(String name,int age);

}

package com.jia.SpringBootDemo.dao;

import com.jia.SpringBootDemo.entry.personEntry;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;


public class UserSqlProvider {
    public String getBadUser(String name, int age) {
        SQL sql = new SQL();
        sql = sql.SELECT("*").FROM("person");
        if (name != null && age != 0) {
            sql.WHERE("name=#{name} and age =#{age}");
        }
        // 永远不成立,查询无果
        else {
            sql.WHERE("1=2");
        }
        return sql.toString();
    }
}

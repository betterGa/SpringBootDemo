package com.jia.SpringBootDemo.Service;

import com.jia.SpringBootDemo.Exception.BizException;
import com.jia.SpringBootDemo.dao.UserMapper;
import com.jia.SpringBootDemo.entry.Person;
import com.jia.SpringBootDemo.entry.personEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<personEntry> list() {
        return userMapper.list();
    }

    @Override
    public List<personEntry> findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<personEntry> getBadUser(String name, int age) {
        return userMapper.getBadUser(name,age);
    }


    // 不写sql查询语句啦，简单构造一下
    static ArrayList<Person> list = new ArrayList<>();


    static {
        list.add(new Person("1", "jia1"));
        list.add(new Person("2", "jia2"));
        list.add(new Person("3", "jia3"));
    }

    public int addPerson(Person person) {
        // 其实 postman 是模拟 HTTP 客户端的，HTTP1.0构造的请求是基于文本的。
        //  只有空字符串的概念而没有 null的概念，这里改成 person.id==null 更合适些。

        if (person == null) {
            throw new BizException("入参为空");
        }
        for (Person person1 : list) {
            if (person1.getId().equals(person.getId())) {
                // 如果数据库中已经有这个 ID 了，需要抛出异常
                throw new BizException("-111", "用户已存在！");
            }
        }
        // 模拟数据插入
        return list.add(person) ? 1 : 0;
       // return 0;
    }


}

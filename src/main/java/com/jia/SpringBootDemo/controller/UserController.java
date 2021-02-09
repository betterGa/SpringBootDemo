package com.jia.SpringBootDemo.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jia.SpringBootDemo.Service.PersonService;
import com.jia.SpringBootDemo.entry.Person;
import com.jia.SpringBootDemo.entry.personEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "用户管理")
@RestController
public class UserController {
    static ArrayList<Person> list = new ArrayList<>();

    static {
        list.add(new Person("1", "jia1"));
        list.add(new Person("2", "jia2"));
        list.add(new Person("3", "jia3"));
    }

    // 查询用户

    @ApiOperation("查询用户")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Person> queryUser(String name) {
        // 不写sql查询语句啦，简单构造一下
        if (name == null) {
            return list;
        }
        ArrayList<Person> ret = new ArrayList<>();
        for (Person person : list) {
            if (name.equals(person.getName())) {
                ret.add(person);
            }
        }
        return ret;
    }


    // 新增用户
    @ApiOperation("新增用户")
    // @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping(value = "/user")
    public List<Person> createUser(@RequestBody Person person) {
        person.setId("4");
        list.add(person);
        return list;
    }


    // 修改用户
    @ApiOperation("修改用户")
    @PutMapping(value = "/user/{id}")
    public List<Person> updateUser(@PathVariable("id") String id) {
        if (id == null) return list;
        for (Person person : list) {
            if (id.equals(person.getId())) {
                person.setName("jiaUpdate");
            }
        }
        return list;
    }

    // 删除用户
    @ApiOperation("删除用户")
    @DeleteMapping(value = "/user/{id}")
    public List<Person> deleteUser(@PathVariable("id") String id) {
        if (id == null) return list;
        for (Person person : list) {
            if (id.equals(person.getId())) {
                list.remove(person);
            }
        }
        return list;
    }

    @Autowired
    private PersonService personService;

    @GetMapping("/getUser")
    public List<personEntry> getUser() {
        List<personEntry> list = personService.list();
        return list;
    }

    @GetMapping("/findByName")
    public List<personEntry> getUserByName(@RequestParam String name) {
        List<personEntry> list = personService.findByName(name);
        return list;
    }

    /**
     * 用于获取结果集的映射关系
     */
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName())
                    .toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n",
                    property, column));
        } stringBuilder.append("})");
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        System.out.println(getResultsStr(personEntry.class));
    }


    @GetMapping("/getBadUser")
    public List<personEntry> getBadUser(@RequestParam String name,@RequestParam int age){
        return personService.getBadUser(name,age);
    }
}

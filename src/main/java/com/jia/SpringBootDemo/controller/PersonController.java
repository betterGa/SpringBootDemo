package com.jia.SpringBootDemo.controller;

import com.jia.SpringBootDemo.Exception.ResponseResult;
import com.jia.SpringBootDemo.Service.PersonServiceImpl;
import com.jia.SpringBootDemo.entry.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class PersonController {

    // 不写sql查询语句啦，简单构造一下
    static ArrayList<Person> list = new ArrayList<>();

    static {
        list.add(new Person("1", "jia1"));
        list.add(new Person("2", "jia2"));
        list.add(new Person("3", "jia3"));
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/addPerson")
    public ResponseResult addPerson(@RequestBody Person person) {
        int ret = personService.addPerson(person);

        // 在将 响应 发送给 客户端 之前,即 在 controller return 之前。
        System.out.println("addPerson Method is calling.");

        return ret == 1 ? ResponseResult.success(null) : ResponseResult.error("新增用户失败");
    }



    @RequestMapping(value = "/user1", method = RequestMethod.GET)
    public ArrayList<Person> queryUser(@RequestParam(name = "name", required = false) String name) {
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


    /* GET 方法
    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    public List<Person> queryUser1(@RequestParam(name = "name", required = false) String name) {
        //{}表示占位符,1 表示一个参数
        /* 方法一
        List list=restTemplate.getForObject("http://localhost:8080/user1?name={1}",List.class,name);
        return list;
         */

        /* 方法二
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List list = restTemplate.getForObject("http://localhost:8080/user1?name={name}", List.class, map);
        return list;
        */

        /* 方法三
        HttpHeaders headers=new HttpHeaders();

        // 接受 Json 数据
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity httpEntity = new HttpEntity(headers);

        List Retlist = restTemplate.exchange("http://localhost:8080/user1?name={1}", HttpMethod.GET,httpEntity,List.class,name)
        .getBody();

        return Retlist;
         */

    // 方法四
        /*
        HttpHeaders headers = new HttpHeaders();
        // 接受 Json 数据
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(headers);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List Retlist = restTemplate.exchange("http://localhost:8080/user1?name={name}", HttpMethod.GET, httpEntity, List.class, map)
                .getBody();
        return Retlist;
    }
    */

    /*
    @PostMapping(value = "/user1")
    public ArrayList<Person> createUser(@RequestBody Person person) {
        list.add(person);
        return list;
    }

    // POST 方法
    @RequestMapping(value = "/user2", method = RequestMethod.POST)
    public List<Person> createUser1(@RequestBody Person person) {
        // 方法一
        /*list = (ArrayList<Person>) restTemplate.postForObject("http://localhost:8080/user1", person, List.class);
        return list;


        // 方法二
        HttpHeaders headers = new HttpHeaders();
        // 接受 Json 数据
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> httpEntity = new HttpEntity(person,headers);

        return restTemplate.exchange("http://localhost:8080/user1",HttpMethod.POST,httpEntity,List.class)
        .getBody();
    }
    */


    // put 方法
    /*
    @PutMapping(value = "/user1/{id}")
    public ArrayList<Person> updateUser(@PathVariable("id") String id, @RequestBody Person person) {
        System.out.println("id from user ====" + id);
        System.out.println("person====" + person);
        for (Person person1 : list) {
            if (id.equals(person1.getId())) {
                list.set(list.indexOf(person1), person);
            }
        }
        return list;
    }
*/

    //@PutMapping(value = "/user2/{id}")
    //public void updateUser1(@PathVariable("id") String id, @RequestBody Person person) {
    // 方法一 没有返回值
    //restTemplate.put("http://localhost:8080/user1/{1}", person, id);

    // 方法二
    /*@PutMapping(value = "/user2/{id}")
    public List<Person> updateUser1(@PathVariable("id") String id, @RequestBody Person person) {
        HttpHeaders headers = new HttpHeaders();
        // 接受 Json 数据
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> httpEntity = new HttpEntity(person, headers);
        return restTemplate.exchange("http://localhost:8080/user1/{1}", HttpMethod.PUT, httpEntity, List.class, id)
                .getBody();
    }*/

    // delete
    @DeleteMapping(value = "/user1/{id}")
    public ArrayList<Person> deleteUser(@PathVariable("id") String id) {
        for (int i=0;i<list.size();i++) {
            if (id != null && list.get(i).getId().equals(id)) {
                list.remove(i);
                i--;
            }
        }
        System.out.println("List" + list);
        return list;
    }

// 方法一
    /*
    @DeleteMapping(value = "/user2/{id}")
    public void deleteUser1(@PathVariable("id") String id) {
        restTemplate.delete("http://localhost:8080/user1/{1}", id);
    }
     */

    // 方法二
    @DeleteMapping(value = "/user2/{id}")
    public List<Person> deleteUser1(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        // 接受 Json 数据
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> httpEntity = new HttpEntity(headers);
        return restTemplate.exchange("http://localhost:8080/user1/{1}", HttpMethod.DELETE, httpEntity, List.class, id)
                .getBody();
    }
}

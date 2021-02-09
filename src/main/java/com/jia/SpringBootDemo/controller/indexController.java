package com.jia.SpringBootDemo.controller;

import com.jia.SpringBootDemo.entry.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;

@Controller
public class indexController {
    @RequestMapping("/index")
    public String index(Model model){
        User user= new User();
        user.setUserName("jia");
        user.setPassword("123456");
        user.setHobbies(Arrays.asList(new String[]{"football","singing","dance"}));
        HashMap<String,String> map=new HashMap();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");
        user.setSecrets(map);
        model.addAttribute("jiaUser",user);
        // 默认找 temple.index.html 这个模板视图文件
        return "index";
    }
}

package com.jia.SpringBootDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {


    @GetMapping("/getItem")
    public String getItem(){
        return "my item is ok;";
    }

}

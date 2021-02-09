package com.jia.SpringBootDemo.Component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


//@Component
@Order(value = 1)
public class SystemPrompt1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("SystemPrompt1: 系统初始化完成");

        // 获取参数方式1
        System.out.println("--- 获取参数 方式1 ---");
        for(String str:args){
            System.out.println("str:" + str);
        }
    }
}

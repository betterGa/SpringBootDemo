package com.jia.SpringBootDemo.Component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(value = 2)
public class SystemPrompt2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("SystemPrompt2: 系统初始化完成");
    }
}
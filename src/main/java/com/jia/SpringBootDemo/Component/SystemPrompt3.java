package com.jia.SpringBootDemo.Component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(value = 3)
public class SystemPrompt3 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("SystemPrompt3: 系统初始化完成");
        // 获取参数 方式1
        System.out.println("------- 获取参数 方式1  -------");
        for (String str : args.getSourceArgs()) {
            System.out.println("str: " + str);
        }
        // 获取参数 方式2
        System.out.println("------- 获取参数 方式2 -------");
        for (
                String str : args.getOptionNames()) {
            System.out.println("key: " + str + " value: " + args.getOptionValues(str));
        }
    }
}

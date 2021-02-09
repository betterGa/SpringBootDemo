package com.jia;

import com.jia.SpringBootDemo.Component.MyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@RestController
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @Bean
    //为 Servlet 配置路径
    public ServletRegistrationBean myServlet() {
        return new ServletRegistrationBean(new MyServlet(), "/dofilter");
    }

    @Bean
    public RestTemplate restTemplate1(){
        return new RestTemplate();
    }
}

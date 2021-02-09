package com.jia.SpringBootDemo.Component;


import com.jia.SpringBootDemo.entry.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

    @Autowired
    Myfilter3 myfilter3;

    /*
    @Bean
    public FilterRegistrationBean registrationProjectFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myfilter3);
        registration.addUrlPatterns("/dofilter");
        return registration;
    }

     */
    //@Bean
    public Person getPerson() {
        return new Person("2", "jia2");
    }


}


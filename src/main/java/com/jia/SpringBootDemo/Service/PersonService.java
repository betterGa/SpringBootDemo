package com.jia.SpringBootDemo.Service;

import com.jia.SpringBootDemo.entry.Person;
import com.jia.SpringBootDemo.entry.personEntry;

import java.util.List;

public interface PersonService {
    public int addPerson(Person person);

    public List<personEntry> list();


    public List<personEntry> findByName(String name);

    public List<personEntry> getBadUser(String name,int age);
}

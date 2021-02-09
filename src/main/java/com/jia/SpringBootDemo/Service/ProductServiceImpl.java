package com.jia.SpringBootDemo.Service;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    public String getProductName() {
        return "football";
    }
}

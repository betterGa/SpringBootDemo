package com.jia.SpringBootDemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    ProductService productService;
    @Override
    public String getProductName() {
        return productService.getProductName();
    }
}

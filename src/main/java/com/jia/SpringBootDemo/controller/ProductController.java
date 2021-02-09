package com.jia.SpringBootDemo.controller;

import com.jia.SpringBootDemo.entry.Products;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    static List<Products> list = new ArrayList<>();

    static {
        list.add(new Products("1", "衣服"));
        list.add(new Products("2", "鞋子"));
        list.add(new Products("3", "帽子"));
    }


    // 查询
    @RequestMapping("/view-products")
    public String viewProducts() {
        return "view-products";
    }

    // 新增
    @RequestMapping("/add-products")
    public String addProducts() {
        return "add-products";
    }

    // 获取商品列表
    @GetMapping("/products")
    @ResponseBody
    public List<Products> getProducts() {
        return list;
    }

    @PostMapping("/products")
    @ResponseBody
    public Products addProducts(@RequestBody Products products) {
        list.add(products);
        return products;
    }

    @RequestMapping("/locale")
    public String locale() {
        return "locale";
    }
}

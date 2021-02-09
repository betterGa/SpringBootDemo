package com.jia;

import com.jia.SpringBootDemo.Service.OrderService;
import com.jia.SpringBootDemo.Service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class SimpleMockTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


    @Test
    public void testProductName(){
       when(productService.getProductName()).thenReturn("mock product name");
       String productName= orderService.getProductName();
       System.out.println(orderService.getProductName());
       Assert.assertEquals("mock product name",productName);
    }














    @Test
    public void simpleTest() {
        // 创建 Mock 对象
        List<String> list = mock(List.class);

        // 当... ... 时... ...
        when(list.get(0)).thenReturn("hello");
        when(list.get(1)).thenThrow(new RuntimeException());

        String s = list.get(0);

        System.out.println(verify(list).get(0));

        Assert.assertEquals("hello", s);
    }

}

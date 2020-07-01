package com.example.hotel.junits;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
/**
*@Description: 测试用的基类，其他类继承此类即可
*@Author: Li Yongshao
*@date: 2020/6/27
*/
public class HotelApplicationTests {
    @Before
    public void init(){
        System.out.println("开始测试------------------------------------");
    }
    @After
    public void after(){
        System.out.println("测试结束------------------------------------");
    }
}

package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by LiuQiulan
 *
 * @date 2018-6-12 17:20
 */
@Controller
@RequestMapping("/redis")
public class RedisTest {
    @Autowired
    private RedisConnectionFactory  factory;

    //redis 初始化连接
    @GetMapping("/get")
    public void test(){
        RedisConnection conn = factory.getConnection();
        conn.set("say".getBytes(),"hello world".getBytes());
        System.out.println("output:"+new String(conn.get("say".getBytes())));
    }

    /*@Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        //创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(factory);
        return template;
    }*/

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/getTemp")
    public String test1(){
        redisTemplate.opsForValue().set("key1","beatiful girl!");
        return redisTemplate.opsForValue().get("key1").toString();
    }
}

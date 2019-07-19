package com.wangkaiyi.demo;

import com.wangkaiyi.demo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
//    操作k-v都是对象的
    @Autowired
    private RedisTemplate redisTemplate;
//    操作k-v都是字符串的
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, User> userRedisTemplate;
    /**
     * Redis常见的五大数据类型
     * String（字符串） List（列表） Set（集合） Hash（散列） ZSet（有序集合）
     * stringRedisTemplate.opsForValue() 字符串
     * stringRedisTemplate.opsForList() 列表
     * stringRedisTemplate.opsForSet()Set散列
     * stringRedisTemplate.opsForHash()Hash集合
     * stringRedisTemplate.opsForZSet()有序集合
     */
    @Test
    public void test1(){
//        给redis中保存数据
//        stringRedisTemplate.opsForValue().append("msg", "hello");
       /* String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println("msg = " + msg);*/
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }
    @Test
    public void test2(){
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("12345");
//        默认如果保存对象，使用jdk序列化机制，徐丽华后的
//        redisTemplate.opsForValue().set("user-01", user);
//        将数据以json的方式保存
//        （1）自己将对象转为json
//         （2）redisTemplate默认的序列化
        userRedisTemplate.opsForValue().set("user-01", user);
    }
}

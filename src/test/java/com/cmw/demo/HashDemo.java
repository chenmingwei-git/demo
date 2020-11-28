package com.cmw.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashDemo{
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void demo1() {
        //put方法
        redisTemplate.opsForHash().put("k1", "name", "baipengfei");
        redisTemplate.opsForHash().put("k1", "age", "22");
        redisTemplate.opsForHash().put("k1", "height", "176");

        //hashKey不存在时，才设值
        //redisTemplate.opsForHash().putIfAbsent(key, hashKey, value)
    }

    @Test
    public void demo2() {
        //putAll方法
        Map<String, String> data = new HashMap<>();
        data.put("name", "jack ma");
        data.put("company", "alibaba");
        data.put("age", "500");
        redisTemplate.opsForHash().putAll("k2", data);
    }

    @Test
    public void demo3() {
        //delete方法，删除key对应的hash的hashkey及其value
        redisTemplate.opsForHash().delete("k2", "name");
    }

    @Test
    public void demo4() {
        //hasKey方法，确定hashkey是否存在
        System.out.println(redisTemplate.opsForHash().hasKey("k2", "name"));
    }

    @Test
    public void demo5() {
        //get方法，根据key和hashkey找出对应的值
        System.out.println(redisTemplate.opsForHash().get("k1", "name"));
    }

    @Test
    public void demo6() {
        //multiGet方法，根据key和多个hashkey找出对应的多个值
        Collection<Object> keys = new ArrayList<>();
        keys.add("name");
        keys.add("age");
        System.out.println(redisTemplate.opsForHash().multiGet("k1", keys));
    }

    @Test
    public void demo7() {
        //increment方法，对key和hashkey对应的值进行增加操作
        //增加长整形（无法对浮点数据使用本方法）
        System.out.println(redisTemplate.opsForHash().increment("k1", "age", 1));
        //增加浮点型（可以对整形数据使用本方法）
        System.out.println(redisTemplate.opsForHash().increment("k1", "age", 1.0));
    }

    @Test
    public void demo8() {
        //keys方法，获取key对应的hash表的所有key
        Set<Object> keys = redisTemplate.opsForHash().keys("k1");
        System.out.println(keys);

        //values方法，获取key对应的hash表的所有value
        List<Object> values = redisTemplate.opsForHash().values("k1");
        System.out.println(values);
    }

    @Test
    public void demo9() {
        //keys方法，获取key对应的hash表的大小
        long size = redisTemplate.opsForHash().size("k1");
        System.out.println(size);
    }

    @Test
    public void demo10() {
        //keys方法，获取key对应的hash表的所有键值对
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("k1");
        System.out.println(entries);
    }
}

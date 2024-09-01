package com.luofan.usercenter.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void test(){
        RList<String> rlist = redissonClient.getList("test-list");
        rlist.add("luofan");
        System.out.println("rlist:"+rlist.get(0));

    }
}

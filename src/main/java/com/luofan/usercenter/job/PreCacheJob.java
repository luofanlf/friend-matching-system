package com.luofan.usercenter.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luofan.usercenter.mapper.UserMapper;
import com.luofan.usercenter.model.domain.User;
import com.luofan.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热任务
 */
@Component
@Slf4j
public class PreCacheJob {

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private RedissonClient redissonClient;

    //重点用户
    List<Long> mainUserList = Arrays.asList(1L);

    //每天执行，预热推荐用户
    @Scheduled(cron = "0 58 23 * * *")
    public void doCacheRecommandUser() {
        RLock lock = redissonClient.getLock("luofan:precachejob:docache:lock");
        try {
            //只有一个线程可以获取到锁
            if (lock.tryLock(0,30000,TimeUnit.MILLISECONDS)) {
                for (Long userId : mainUserList) {
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1,20),queryWrapper);
                    String redisKey = String.format("luofan:user:recommend:%s", userId);
                    ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
                    try {
                        valueOperations.set(redisKey,userPage,30000, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        log.error("redis set key error",e);
                    }
                }
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommandUser error",e);
        }   finally {
            //只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}

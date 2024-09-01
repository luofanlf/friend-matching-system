package com.luofan.usercenter.once;
import java.util.Date;

import com.luofan.usercenter.mapper.UserMapper;
import com.luofan.usercenter.model.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InsertUsers {

    @Resource
    private UserMapper userMapper;

    /**
     * 批量插入数据
     */
    public void insertUsers() {
        final int INSERT_NUM = 100000000;
        for(int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("");
            user.setUserAccount("");
            user.setAvatarUrl("");
            user.setGender(0);
            user.setUserPassword("");
            user.setPhone("");
            user.setEmail("");
            user.setUserStatus(0);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setIsDelete(0);
            user.setUserRole(0);
            user.setPlanetCode("");
            user.setTags("");
            userMapper.insert(user);

        }
    }
}

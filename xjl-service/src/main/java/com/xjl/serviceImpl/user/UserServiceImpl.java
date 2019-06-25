package com.xjl.serviceImpl.user;


import com.github.pagehelper.PageHelper;
import com.xjl.dao.user.UserMapper;
import com.xjl.entity.user.User;
import com.xjl.service.user.UserService;
import com.xjl.utils.RedisService;
import com.xjl.vo.common.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2018/11/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 登录
     */
    @Override
    public PageBean<User> selectUserList() {
        PageHelper.startPage(1,10);
        List<User> list = userMapper.selectAll();
        redisService.set("sss",111);
        return new PageBean<>(list);
    }

    /**
     * 添加用户
     * @param id
     * @param nickname
     */
    @Override
    @Transactional
    public void insertUser(Long id, String nickname) {
        User user = new User();
        user.setId(id);
        user.setNickname(nickname);
        userMapper.insert(user);
    }
}

package com.xjl.service.user;


import com.xjl.entity.user.User;
import com.xjl.vo.common.PageBean;

/**
 * Created by lenovo on 2018/11/29.
 */

public interface UserService {

    /**
     * 登录
     */
    PageBean<User> selectUserList();

    /**
     * 添加用户
     * @param id
     * @param nickname
     */
    void insertUser(Long id, String nickname);
}

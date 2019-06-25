package com.xjl.dao.user;


import com.xjl.dao.common.BaseDao;
import com.xjl.entity.user.User;

import java.util.List;

/**
 * Created by lenovo on 2018/11/29.
 */
public interface UserMapper extends BaseDao<User> {

    List<User> selectAll();

}

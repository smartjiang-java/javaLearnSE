package com.jqk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jqk.mapper.UserMapper;
import com.jqk.service.UserService;
import com.jqk.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/22 10:12
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getSome(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 27);
        queryWrapper.ge("id", 1);

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.remove();
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public List<User> getUser(User user) {
        if(null==user){
            return  userMapper.getAllStu();
        }
        return userMapper.getAllStu(user.getId());
    }
}
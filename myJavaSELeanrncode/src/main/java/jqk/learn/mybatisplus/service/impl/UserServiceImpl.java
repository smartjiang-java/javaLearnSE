package jqk.learn.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jqk.learn.mybatisplus.mapper.UserMapper;
import jqk.learn.mybatisplus.service.UserService;
import jqk.learn.mybatisplus.vo.User;
import org.springframework.stereotype.Repository;

/**
 * @Author:JQK
 * @Date:2021/2/5 15:26
 * @Package:jqk.learn.mybatisplus.service.impl
 * @ClassName:UserServiceImpl
 **/

@Repository
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}

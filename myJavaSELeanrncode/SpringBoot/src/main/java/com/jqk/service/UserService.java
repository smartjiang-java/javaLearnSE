package com.jqk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jqk.vo.User;

import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/22 10:08
 **/

public interface UserService extends IService<User> {

    /**
     * 获取用户集合
     *
     * @param user 传进来的user参数
     * @return 通过user条件，返回符合条件的User集合
     */
    List<User> getSome(User user);

    /**
     *
     * @param user 传进来的user参数
     * @return 通过user条件，返回符合条件的User集合
     */
    List<User> getUser(User user);

    List<User> selectForeachList2(List<User> users);

}
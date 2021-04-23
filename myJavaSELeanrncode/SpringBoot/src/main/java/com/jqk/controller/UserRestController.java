package com.jqk.controller;

import com.jqk.service.UserService;
import com.jqk.vo.User;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/21 15:16
 **/

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final Log log = LogFactory.get();

    @Resource
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> users = userService.getSome(null);
        log.info("返回的结果集数量为：{}，结果集是：{}", users.size(),users);
        return users;
    }

}

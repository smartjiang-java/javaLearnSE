package com.jqk.controller;

import com.jqk.service.DocDirService;
import com.jqk.service.UserService;
import com.jqk.vo.DocDir;
import com.jqk.vo.User;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/21 15:16
 **/

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final Log log = LogFactory.get();

    @Autowired
    private UserService userService;

    @Autowired
    private DocDirService docDirService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> users =userService.query().eq("age","24").eq("id","2").list();
        log.info("返回的结果集数量为：{}，结果集是：{}", users.size(), users);
        return users;
    }

}

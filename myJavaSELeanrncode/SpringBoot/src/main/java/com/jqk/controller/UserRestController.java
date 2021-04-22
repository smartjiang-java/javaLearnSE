package com.jqk.controller;

import com.jqk.service.UserService;
import com.jqk.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Resource
    private UserService userService;


    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        logger.info("打了一个日志，{}","嘿嘿嘿");
        return  userService.getSome(null);
    }

}

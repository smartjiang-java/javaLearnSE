package jqk.learn.mybatisplus.controller;

import jqk.learn.mybatisplus.service.UserService;
import jqk.learn.mybatisplus.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:JQK
 * @Date:2021/2/5 15:40
 * @Package:jqk.learn.mybatisplus.controller
 * @ClassName:UserController
 **/

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getallUsers")
    public List<User>  getAllUsers(){
        return  userService.list();

    }
}





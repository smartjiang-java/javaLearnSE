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
        List<User> users = userService.getSome(null);
        log.info("返回的结果集数量为：{}，结果集是：{}", users.size(), users);
        return users;
    }

    @GetMapping("/test")
    public void getUsers() {
        User user = new User("1", null, null);
        System.out.println(userService.getUser(null));
        System.out.println(userService.getUser(user));
    }

    @GetMapping("/test2")
    public void getUsers2() {
        User user = new User("1", null, 1);
        User use2 = new User("2", null, 2);
        User use3 = new User("3", null, 3);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(use2);
        users.add(use3);
        userService.selectForeachList2(users).forEach(System.out::println);
    }

    @GetMapping("/test3")
    public DocDir getUsers3() {
        DocDir docDir = docDirService.selectById("1");
        System.out.println(docDir);
        return  docDir;
    }
    @GetMapping("/test4")
    public  List<DocDir> getUsers4() {
        List<DocDir> docDirs = docDirService.selectByParentId("1");
        System.out.println(docDirs);
        return  docDirs;
    }


}

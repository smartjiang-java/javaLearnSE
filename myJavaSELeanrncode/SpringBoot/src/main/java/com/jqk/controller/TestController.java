package com.jqk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 15:55
 **/

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return  "hello";
    }

}

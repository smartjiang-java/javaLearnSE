package com.jqk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:jiangqikun
 * @Date:2021/4/21 15:16
 **/

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @GetMapping("/get")
    public String getString() {
        logger.info("打了一个日志，{}","嘿嘿嘿");
        return "I am test method!";
    }
}

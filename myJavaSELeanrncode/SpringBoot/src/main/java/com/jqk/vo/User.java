package com.jqk.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author:jiangqikun
 * @Date:2021/4/22 10:06
 **/

@Data
@TableName("user")
public class User{
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
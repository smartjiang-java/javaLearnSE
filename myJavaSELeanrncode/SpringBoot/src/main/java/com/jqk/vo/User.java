package com.jqk.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author:jiangqikun
 * @Date:2021/4/22 10:06
 **/

@Data
@TableName("user")
@ApiModel(value="User对象",description="User角色对象")
public class User{

    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
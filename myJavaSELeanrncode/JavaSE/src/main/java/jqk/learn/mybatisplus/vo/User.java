package jqk.learn.mybatisplus.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author:JQK
 * @Date:2021/2/5 15:20
 * @Package:jqk.learn.mybatisplus
 * @ClassName:User
 **/
@Data
@TableName("user")
public class User extends Model<User> {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
}

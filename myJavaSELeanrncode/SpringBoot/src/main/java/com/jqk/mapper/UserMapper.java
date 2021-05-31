package com.jqk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jqk.vo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/22 10:07
 **/

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 下面两个测试:dao层里面是否可以使用重载方法，使用动态sql是可以达到的，对应同一个xml里面的id
     */
    List<User> getAllStu();

    List<User> getAllStu(@Param("id") String id);


}

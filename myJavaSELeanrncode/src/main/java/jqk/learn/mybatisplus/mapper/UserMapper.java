package jqk.learn.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jqk.learn.mybatisplus.vo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:JQK
 * @Date:2021/2/5 15:27
 * @Package:jqk.learn.mybatisplus.mapper
 * @ClassName:UserMapper
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

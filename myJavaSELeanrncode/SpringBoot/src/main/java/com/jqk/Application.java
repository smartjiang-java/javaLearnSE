package com.jqk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 15:53
 * <p>
 * 注意:热部署使用 ctrl+shift+F9快捷键刷新
 * <p>
 * 缘由
 * 应用没有使用到DataSource，可是在pom.xml里引入了mybatis-spring-boot-starter spring
 * <p>
 * 问题解决办法有两种：
 * 把mybatis-spring-boot-starter的依赖去掉，这样就不会触发spring boot相关的代码
 * 把spring boot自动初始化DataSource相关的代码禁止掉
 **/

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@SpringBootApplication
@MapperScan(basePackages = "com.jqk.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

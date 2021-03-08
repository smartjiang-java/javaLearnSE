package jqk.learn.javase.foundtion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author:JQK
 * @Date:2020/11/30 13:15
 * Description:
 * ClassNmae:JDBCDemo
 * Package:jqk.learn.javase.test.jdbc
 **/

public class JdbcDemo {

    /**
     * 使用JDBC提供一个一对多的关系，供多个数据库实现
     * 设计思路：根据OOP的原则，接口隔离，一个接口一个功能
     * Connection接口：连接数据库
     * Statement,PrepareStatement接口：操作数据库
     * ResultSet接口:操作返回的结果集
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //通过反射加载驱动类,得到一个对象
        Class<?> forName = Class.forName("com.mysql.jdbc.Driver");
        //通过驱动经理，根据四要素连接数据库
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8", "root", "admin");
        //
        System.out.println(connection);

    }

}

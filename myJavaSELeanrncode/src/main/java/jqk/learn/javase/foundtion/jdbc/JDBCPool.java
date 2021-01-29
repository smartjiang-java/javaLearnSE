package jqk.learn.javase.foundtion.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author:JQK
 * @Date:2020/12/23 11:18
 * @Package:jqk.learn.javase.foundtion.jdbc
 * @ClassName:JDBCPool 自定义数据库连接池：连接池的减少和增加
 * 数据库连接池, 实现DataSource接口,
 * 增删改比较多，使用链表实现
 * 注意：这里使用了适配器模式（减少实现的方法） 和代理模式
 **/

public class JDBCPool {
    public static void main(String[] args) throws SQLException {
        //向上转型不能访问子类特有的方法
        DataSource dataSource = new BlueDataSource();
        //获取连接
        Connection connection = dataSource.getConnection();
        //关闭连接
        connection.close();
    }
}

class BlueDataSource extends DataSourceAdapter {

    private final List<Connection> POOL = new LinkedList<>();

    //初始化数据库连接池
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone" +
                        "=GMT%2B8", "root", "admin");
                Connection proxy = (Connection) Proxy.newProxyInstance(BlueDataSource.class.getClassLoader(),
                        new Class[]{Connection.class}, new BlueInvocationHandler(connection, POOL));
                POOL.add(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (POOL.isEmpty()) {
            return null;
        }
        //每次都拿到第一个，通过remove()方法
        Connection remove = POOL.remove(0);
        System.out.println("连接成功，此时连接池数量为："+POOL.size());
        return remove ;
    }

    /**
     * 使用内部类实现代理，不使用匿名内部类
     */
    private static class BlueInvocationHandler implements InvocationHandler {
        private final Connection connection;
        private final List<Connection> POOL;

        public BlueInvocationHandler(Connection connection, List<Connection> POOL) {
            this.connection = connection;
            this.POOL = POOL;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("close")) {
                POOL.add(connection);
                System.out.println("回收成功，此时连接池数量为："+POOL.size());
                return null;
            }
            return method.invoke(connection, args);
        }
    }
}


class DataSourceAdapter implements DataSource {

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}







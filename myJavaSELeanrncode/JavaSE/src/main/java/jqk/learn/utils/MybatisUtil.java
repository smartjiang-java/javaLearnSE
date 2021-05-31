package jqk.learn.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


/**
 * @Author:jiangqikun
 * @Date:2021/5/31 15:40
 **/

public class MybatisUtil {

    public static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {
        try {
            if (null == sqlSessionFactory) {
                //Resources类读取主配置文件
                InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
                //创建SqlSessionFactory:是一个重量级对象（系统开销大），线程安全的，所以一个应用创建一个即可
                //输入流使用后无需手动关闭，SqlSessionFactoryBuilder().build()有一个自动关流的操作
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // .openSession()同 .openSession(false),非自动提交，需要手动提交; .openSession(true),自动提交
        //一个SqlSession对应一次数据库会话，线程不安全的，所以需要在会话结束，将其关闭，无需手动回滚
        //将事务的自动提交赋值为false（变量dirty），创建一个执行器对象，用于执行映射文件中的sql文件，
        return sqlSessionFactory.openSession();
    }
}

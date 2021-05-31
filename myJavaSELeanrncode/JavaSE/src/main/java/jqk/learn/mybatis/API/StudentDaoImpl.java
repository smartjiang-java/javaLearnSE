package jqk.learn.mybatis.API;

import jqk.learn.utils.MybatisUtil;
import jqk.learn.vo.Student;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author:jiangqikun
 * @Date:2021/5/31 15:39
 **/

public class StudentDaoImpl implements IStudentDao {

    private SqlSession sqlSession;

    @Override
    public void insertStudent(Student student) {
        try {
            sqlSession = MybatisUtil.getSqlSession();
            //无论是增删改，底层调用的都是对数据继续修改，均将dirty变量设置为true,获取到映射文件的指定id的sql语句后,由执行器executor执行
            sqlSession.insert("insertStu", student);
            //调用无参的commit（）,会先将dirty变量设置为true，执行提交后，再将dirty设置为false
            sqlSession.commit();

        } finally {
            if (null != sqlSession) {
                //若没有被提交，回滚后关闭；若已提交，直接关闭   将dirty置为false
                sqlSession.close();
            }
        }
    }

}

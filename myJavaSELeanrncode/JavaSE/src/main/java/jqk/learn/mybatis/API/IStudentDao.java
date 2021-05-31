package jqk.learn.mybatis.API;

import jqk.learn.vo.Student;

/**
 * @Author:jiangqikun
 * @Date:2021/5/31 15:37
 **/

public interface IStudentDao {

    /**
     * 向数据库表中插入一条数据
     * @param student  student对象
     */
    void insertStudent(Student student);
}

package jqk.learn.javase.foundtion.reflect;

import java.lang.reflect.Field;

/**
 * @Author:JiangQiKun
 * @Date:2020/11/30 23:20
 * Description:
 * jqk.learn.javase.test.reflectReflectDemo
 */

class  Student{
    private  int Sno=13101205;
    private String name="木鱼脑袋";
    private  int age=100;

    @Override
    public String toString() {
        return "Student{" +
                "Sno=" + Sno +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        //拿到student的成员变量
        //1:new一个对象,调用get()方法
        //2:反射 Student.class;
        Class<Student> clazz = Student.class;
        Field name = clazz.getDeclaredField("name");
        //允许修改值,反射不能解final
        name.setAccessible(true);
        Student newInstance = clazz.newInstance();
        //修改值:如果成员变量是static,可以传null,否则,必须要传一个对象
        //但是有了反射,不用new了,调用方法即可,这个方法必须提供一个无参构造器
        //如果要加入参数来创建对象,那么这种方法不可取
        //通过这里我们可以看到spring中bean里面的依赖的属性可以通过这种方式注入
        name.set(newInstance,"其他类型");
        System.out.println(newInstance);
    }
}

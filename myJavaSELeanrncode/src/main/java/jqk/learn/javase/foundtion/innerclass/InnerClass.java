package jqk.learn.javase.foundtion.innerclass;

import org.junit.Test;

/**
 * 内部类
 * @Date:2020/10/9
 * @Author:JQK
 **/

public class InnerClass {

    private static int i = 100;

    @Test
    public void test() {

        //调用匿名内部类
       new StaticClass().print();
        //StaticClass.print();

        /*
        java中的类的定义方式有哪几种？
         * 普通定义
         * 静态内部类
         * 普通内部类
         * 匿名类
         */
    }

    //静态内部类
    //是静态的内部类，那么从字面上来讲它是不是和静态的方法和属性一样 也就是可以通过类名.类名来使用
    //而且都说了他是内部类，所谓内部是啥意思？是不是说这个类定义在一个类的内部。
    //内部类的好处：可以共享所在类的内部中的变量，而不用像访问普通类一样需要把共享的参数传递过去
   static class StaticClass {
        public  void print() {
            System.out.println(i);
        }
    }
}



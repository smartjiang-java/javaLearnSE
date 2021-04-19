package interfaces;

import org.junit.Test;

/**
 * @Author:jiangqikun
 * @Date:2021/4/19 10:25
 **/

public class InterfaceTest {

    @Test
    public void test() {
        MyInterface.show();
    }
}

interface MyInterface {
    default void print() {
        System.out.println("接口中的默认方法");
    }

    static void show() {
        System.out.println("接口中的静态方法");
    }

}

class FatherClass {
    void get() {
        System.out.println("父类中的方法");
    }
}

class SubClass extends FatherClass implements MyInterface {

}


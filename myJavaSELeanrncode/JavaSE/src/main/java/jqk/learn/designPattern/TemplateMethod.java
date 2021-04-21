package jqk.learn.designPattern;

/**
 * @Author:JQK
 * @Date:2020/12/28 20:04
 * @Package:jqk.learn.special.design
 * @ClassName:TemplateMethod
 **/

//[模板模式，在父类也就是抽象类中对接口部分方法进行实现,留出一部分方法给子类进行实现]。定义了一个算法也就是
// 模板， 子类只需要实现某个方法即可获得全部算法实现。

/**
 * 继承一个抽象类，只需要实现抽象类的抽象方法即可
 * @author 16770
 */
public class TemplateMethod  extends MyTest{
    @Override
    public void test2() {
        System.out.println("test2");
    }

    @Override
    public void test1() {
        System.out.println("重写了抽象类已经实现的方法");
    }

    public static void main(String[] args) {
        TemplateMethod method = new TemplateMethod();
        method.test1();
        method.test2();
    }

}

interface Test{

    void test1();

    void test2();
}

/**
 * 抽象类：抽象类就是不能使用new方法进行实例化的类，即没有具体实例对象的类；只可以通过抽象类派生出新的子类，再由其子类来创建对象
 * 抽象类去实现接口，不需要实现接口的全部方法
 */
abstract class MyTest implements  Test{

    @Override
    public  void test1() {
        System.out.println("test1");
    }

    @Override
    public abstract void test2();
}
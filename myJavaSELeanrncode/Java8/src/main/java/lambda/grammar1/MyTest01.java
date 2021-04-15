package lambda.grammar1;

import org.junit.Test;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 10:04
 * <p>
 * Lambda表达式的本质是：接口的实现类的具体的对象
 * @FunctionalInterface 修饰函数式接口的，接口中的抽象方法只有一个 (除了equals方法)
 * <p>
 * 语法： ()->{}
 * 1:()中参数类型要么都不省略，要么都省略
 * 2：参数的数量只有一个，（）可以省略
 * 3：方法体只有一句，{}可以省略；如果方法体只有一句，且有返回值，return也可以省略
 **/
public class MyTest01 {

    @Test
    public void test() {
        LambdaSingleReturnMutipleParmeter lambdaSingleReturnMutipleParmeter = (a, b) -> {
            System.out.println("------------");
            return a + b;
        };
        lambdaSingleReturnMutipleParmeter.test(10,20);
    }
}

@FunctionalInterface
interface LambdaSingleReturnMutipleParmeter {
    int test(int a, int b);
}


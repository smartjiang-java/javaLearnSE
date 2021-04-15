package lambda.grammar2;

import org.junit.Test;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 10:22
 * <p>
 * Lambda语法进阶：方法引用：可以将一个Lambda表达式的实现指向一个已经实现的方法
 * 一个接口在很多地方调用同一个实现
 * 方法的隶属者 如果是静态方法 隶属的就是一个类  其他的话就是隶属对象this
 * 语法：方法的隶属者::方法名
 * 注意：
 * 1.引用的方法中，参数数量和类型一定要和接口中定义的方法一致
 * 2.返回值的类型也一定要和接口中的方法一致
 **/
public class TestMethod {

    private static int change(int a) {
        return a * 2;
    }

    private  int changes(int a) {
        return a * 2;
    }

    @Test
    public void test(){
        LambdaSingleReturnSingleParmeter lambdaSingleReturnSingleParmeter=n -> change(n);
        LambdaSingleReturnSingleParmeter lambdaSingleReturnSingleParmeter2=TestMethod::change;

        LambdaSingleReturnSingleParmeter lambdaSingleReturnSingleParmeter1= this::changes;
    }

}

@FunctionalInterface
interface LambdaSingleReturnSingleParmeter {
    int test(int n);
}

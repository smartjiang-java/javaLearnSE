package lambda.use.builtInInterface;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/17 23:32
 * Description:
 * lambda.use.consumerTestConsumer
 */
public class MyTest {

    @Test
    public void test() {
        consumer(1000,m-> System.out.println("消费了" + m + "元"));
        supplier(()-> 100);
        function(100,m->50.00);
        predicate(100,m->true);
    }

    /**
     *  Consumer<T>        #      void accept(T t)             :消费型接口
     */
    public void consumer(double money , Consumer<Double> con){
        con.accept(money);
    }

    /**
     *  Supplier<T>        #      T get()                      :供给型接口
     */
    public Integer supplier(Supplier<Integer> con){
        return con.get();
    }

    /**
     *   Function<T, R>     #      R apply(T t);                :函数型接口,用于对象的转换
     */
    public Double function(Integer integer,Function<Integer,Double> con){
        return con.apply(integer);
    }

    /**
     *   Predicate<T>       #      boolean test(T t)            :断言型接口,用于做一些判断操作
     */
    public boolean predicate(Integer integer, Predicate<Integer> con){
        return con.test(integer);
    }
}

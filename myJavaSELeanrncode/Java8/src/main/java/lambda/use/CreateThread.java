package lambda.use;

import org.junit.Test;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 11:28
 **/

public class CreateThread {
    @Test
    public void test() {
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        Thread t=new Thread(()->{
            System.out.println(100);
        });

    }


    // Predicate<T>              ：     参数是T 返回值boolean,判断输入的对象是否符合某个条件 例如list.removeif()
    // 在后续如果一个接口需要指定类型的参数，返回boolean时可以指向 Predicate
    //          IntPredicate            int -> boolean
    //          LongPredicate           long -> boolean
    //          DoublePredicate         double -> boolean

    // Consumer<T>               :      参数是T 无返回值(void),对给定参数执行消费操作 例如list.foreach()
    //          IntConsumer             int ->void
    //          LongConsumer            long ->void
    //          DoubleConsumer          double ->void

    // Function<T,R>             :      参数类型T  返回值R,根据一个类型的数据得到另一个类型的数据
    //          IntFunction<R>          int -> R
    //          LongFunction<R>         long -> R
    //          DoubleFunction<R>       double -> R
    //          IntToLongFunction       int -> long
    //          IntToDoubleFunction     int -> double
    //          LongToIntFunction       long -> int
    //          LongToDoubleFunction    long -> double
    //          DoubleToLongFunction    double -> long
    //          DoubleToIntFunction     double -> int

    // Supplier<T> : 参数 无 返回值T  ,用来获取一个泛型参数指定类型的对象数据
    // UnaryOperator<T> :参数T 返回值 T   继承Function<T,R> 接口
    // BiFunction<T,U,R> : 参数 T、U 返回值 R
    // BinaryOperator<T> ：参数 T、T 返回值 T   继承BiFunction<T,U,R>接口
    // BiPredicate<T,U> :  参数T、U  返回值 boolean
    // BiConsumer<T,U> :    参数T、U 无返回值

    /**
     * 常用的 函数式接口
     * Predicate<T>、Consumer<T>、Function<T,R>、Supplier<T>
     */
}


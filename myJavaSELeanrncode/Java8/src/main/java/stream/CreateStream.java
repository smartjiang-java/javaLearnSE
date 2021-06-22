package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/18 0:51
 * Description:
 * stream.createCreateStream
 */
public class CreateStream {
    /**
     * 创建流
     */
    public static void main(String[] args) {
        // 1:通过Collection接口的stream()创建串行流,parallelStream()创建并行流
        List<String> list =new ArrayList<>();
        Stream<String> stream1 = list.stream();
        Stream<String> stream = list.parallelStream();

        //2:通过数组类Arrays的静态方法stream()
        Integer [] array=new Integer[10];
        Stream<Integer> stream2 = Arrays.stream(array);
        stream2.limit(5).forEach(System.out::println);

        //3:通过Stream类的静态方法of(),可变参数
         Stream<String> stream3 = Stream.of("aa");

         //4:创建无限流:一直运行的流
         //迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
         //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
    }
}

package parallelStreamn;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @Author:jiangqikun
 * @Date:2021/4/19 9:37
 * <p>
 * 并行流：底层是 fork/join 框架
 **/

public class ParallelStream {

    @Test
    public void test() {

        //java 8 获取当前时间
        Instant start = Instant.now();

        //使用并行流对 0 -1000000000000进行累加
        LongStream.rangeClosed(0, 1000000000).parallel().reduce(Long::sum);

        Instant end = Instant.now();

        //根据时间，是通过分钟还是秒，毫秒显示
        long millis = Duration.between(start, end).toMillis();

        System.out.println("累加耗费时间为：" + millis);
    }
}

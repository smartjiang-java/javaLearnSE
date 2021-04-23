package time;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/23 0:52
 * Description:
 * timeSimpleDateFormateTest
 */
public class SimpleDateFormateTest {

    private final Log log = LogFactory.get();

    @Test
    public void test() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> callable = () -> simpleDateFormat.parse("20161218");

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(threadPool.submit(callable));
        }
        if (!CollectionUtils.isEmpty(results)) {
            results.stream().map(str -> {
                try {
                    return str.get();
                } catch (Exception e) {
                    log.error("出现异常：{}", e);
                }
                //返回空集合，而非null
                return Collections.emptyList();
            }).forEach(System.out::println);
        }
        threadPool.shutdown();

    }

    /**
     * java 8
     */
    @Test
    public void test2() {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> callable = () -> LocalDate.parse("20161218", df);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(threadPool.submit(callable));
        }
        if (!CollectionUtils.isEmpty(results)) {
            results.stream().map(str -> {
                try {
                    return str.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).forEach(System.out::println);
        }
        threadPool.shutdown();

    }
}

package time;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/23 1:22
 * Description:
 * timeSimpleFormateThreadLocal
 */
public class SimpleFormateThreadLocal {

    @Test
    public void test() {

        Callable<Date> callable = () -> SimpleFormateThreadLocal.convert("20161218");

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
                    e.printStackTrace();
                }
                return null;
            }).forEach(System.out::println);
        }
        threadPool.shutdown();

    }

    private static final ThreadLocal<DateFormat> tl = new ThreadLocal() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return tl.get().parse(source);
    }


}

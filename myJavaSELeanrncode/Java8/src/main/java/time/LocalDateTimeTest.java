package time;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.Test;

import java.time.*;

/**
 * @Author:jiangqikun
 * @Date:2021/4/23 11:07
 *
 * 1:LocalDate LocalTime   LocalDateTime
 * 2:计算机读的时间格式Instant:时间戳（以Unix 元年：1970年1月1日 00:00:00 到某个时间的毫秒值）
 * 3：Duration:计算两个'时间'之间的间隔
 *    Period:计算两个'日期'之间的间隔
 **/

public class LocalDateTimeTest {

    private final Log log = LogFactory.get();

    @Test
    public void test() {
        //通过静态方法获取当前系统时间，精确到毫秒
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("当前系统时间：{}", localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2015, 10, 19, 13, 22, 22);
        log.info("自定义系统时间：{}", localDateTime1);
        //对日期进行加，可以加年份，月份，日,产生新的实例
        LocalDateTime localDateTime2 = localDateTime.plusYears(2);
        log.info("对日期进行加：{}", localDateTime2);
        //对日期进行减
        LocalDateTime localDateTime3 = localDateTime.minusYears(1);
        log.info("对日期进行减：{}", localDateTime3);

        //获取年，月，日
        int year = localDateTime.getYear();
        int minute = localDateTime.getMinute();
        int month = localDateTime.getMonth().getValue();
    }

    @Test
    public void test2() {
        Instant now = Instant.now();
        log.info("默认获取UTC时区的时间：{}", now);
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        log.info("带偏移量的UTC时区的时间：{}",offsetDateTime);
        long milli = now.toEpochMilli();
        log.info("默认获取UTC时区的时间毫秒值：{}",milli);
        Instant instant = Instant.ofEpochSecond(1000);
        log.info("时间戳增加秒值：{}",instant);
    }

    @Test
    public void test3() {
        Instant now = Instant.now();
        Instant now2 = Instant.now();
        Duration duration = Duration.between(now, now2);
        long seconds = duration.getSeconds();
        int nano = duration.getNano();
        long l = duration.toMillis();
        long l1 = duration.toDays();

        LocalDate localDate=LocalDate.now();
        LocalDate localDate1= LocalDate.now();
        Period period = Period.between(localDate, localDate1);
        int days = period.getDays();
        int years = period.getYears();
        int months = period.getMonths();
    }
}

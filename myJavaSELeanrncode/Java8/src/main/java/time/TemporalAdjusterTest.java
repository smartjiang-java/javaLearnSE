package time;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @Author:jiangqikun
 * @Date:2021/4/23 15:04
 * <p>
 * TemporalAdjuster：时间校正器
 **/

public class TemporalAdjusterTest {

    private final Log log = LogFactory.get();

    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(10);
        log.info("改变月中的天：{}", localDateTime1);

        //通过时间校正器改变
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SATURDAY);
        LocalDateTime localDateTime2 = localDateTime.with(temporalAdjuster);
        log.info("下一个周六：{}", localDateTime2);
        //自定义校正器
        LocalDateTime localDateTime4 = localDateTime.with((str) -> {
            LocalDateTime localDateTime3 = (LocalDateTime) str;
            DayOfWeek dayOfWeek = localDateTime3.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDateTime3.plusDays(1);
            }
            return null;
        });
        log.info("下一个周六：{}", localDateTime4);
    }
}

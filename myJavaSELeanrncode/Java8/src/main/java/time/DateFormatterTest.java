package time;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author:jiangqikun
 * @Date:2021/4/23 15:20
 **/

public class DateFormatterTest {

    private final Log log = LogFactory.get();

    @Test
    public void test() {
        DateTimeFormatter simpleDateFormatter = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = simpleDateFormatter.format(localDateTime);
        log.info("格式化日期后：{}", format);

        //自定义格式化格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = dateTimeFormatter.format(localDateTime);
        log.info("自定义格式化日期后：{}", format1);
        //再转回以前的格式
        LocalDateTime ld= LocalDateTime.parse(format1,dateTimeFormatter);
        log.info("z再转回自定义格式化日期以前的格式：{}",ld);

    }
}

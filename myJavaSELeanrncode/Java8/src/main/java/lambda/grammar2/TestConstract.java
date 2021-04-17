package lambda.grammar2;

import lombok.Data;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/18 0:29
 * Description:
 * lambda.grammar2TestConstract
 */
public class TestConstract {

    /**
     * 构造器引用
     */
    @Test
    public void test() {
        Supplier<People> supplier = () -> new People();
        Supplier<People> sp = People::new;
    }

    /**
     * 数组引用
     */
    @Test
    public void test2() {
        Function<Integer, String[]> function = x -> new String[x];
        Function<Integer, String[]> fu=String[] :: new;
    }

}

@Data
class People {
    private String name;
    private Integer age;
}
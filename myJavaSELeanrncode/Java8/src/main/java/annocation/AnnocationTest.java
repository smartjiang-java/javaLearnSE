package annocation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author:jiangqikun
 * @Date:2021/4/19 10:49
 **/

public class AnnocationTest {

    @Test
    public void test() throws NoSuchMethodException {
        Class<AnnocationTest> clazz = AnnocationTest.class;
        Method show = clazz.getMethod("show");
        Arrays.stream(show.getAnnotationsByType(MyAnnocation.class)).map(MyAnnocation::value).forEach(System.out::println);
    }

    @MyAnnocation("hello")
    @MyAnnocation("world")
    @MyAnnocation("end")
    public void show() {

    }

    public void type( @MyAnnocation("abc") String str){

    }

}

package optional;

import org.junit.Test;
import vo.Person;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @Author:jiangqikun
 * @Date:2021/4/19 9:43
 **/

public class OptionalTest {

    /**
     * Optional.of(T t)：根据传入的值创建一个Optional实例,t 不能为null
     * Optional.empty():创建一个空的Optional实例
     * Optional.ofNullable(T t):若 t 不为null,创建Optional实例，否则创建空实例。
     * Optional.isPresent():判断是否包含值
     * Optional.get（）：获取Optional中的值
     * Optional.orElse(T t):如果调用对象包含值，返回该值，否则返回t
     * Optional.orElseGet(Supplier s):如果调用对象包含值，返回改值，否则返回s,传入供给型接口
     * Optional.map(Function f):如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * Optional.flatMap(Function mapper):与Optional.map(Function f)类似，要求返回值必须是Optional
     */

    @Test
    public void test() {
        Optional<Person> op = Optional.of(new Person("wangba", 28));
        Person person = op.get();
        Optional<Integer> integer = op.map(Person::getAge);
        op.flatMap(e -> Optional.of(e));

        Optional<String> empty = Optional.empty();

        Optional<Object> o = Optional.ofNullable(null);
        if (o.isPresent()) {
            Object o1 = o.get();
            System.out.println(o1);
        }
        Object o2 = o.orElse("else值");
        System.out.println(o2);
        Person person1 = op.orElse(null);
        System.out.println(person1);
        final Object o3 = o.orElseGet(() -> "orElse值");
        System.out.println(o3);


        ArrayList<Integer> list = new ArrayList<>();
        list.add(111);
        list.add(222);
        Optional<ArrayList<Integer>> list1 = Optional.of(list);
        ArrayList<Integer> integers = list1.get();
        integers.forEach(System.out::println);


    }

}

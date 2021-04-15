package lambda.use;

import lambda.use.vo.Person;
import org.junit.Test;

import java.util.*;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 11:10
 **/

public class TreeSetSort {

    Set<Person> returnSet() {

//       TreeSet<Person> set =new TreeSet<>(new Comparator<Person>() {
//           @Override
//           public int compare(Person o1, Person o2) {
//               return 0;
//           }
//       });

        TreeSet<Person> set = new TreeSet<>((o1, o2) -> o1.getAge() - o2.getAge());
        set.add(new Person("张三", 10));
        set.add(new Person("李四", 12));
        set.add(new Person("王五", 13));
        set.add(new Person("赵六", 14));
        set.add(new Person("李雷", 11));
        set.add(new Person("韩梅梅", 8));
        set.add(new Person("jack", 10));
        return set;
    }

    @Test
    public void test() {
        Set<Person> returnSet = returnSet();
        System.out.println(returnSet);
    }

}

package lambda.use.collection;

import vo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 11:01
 *
 * ArrayList排序， list.sort( new Comparator{} )
 *  主要是针对 Comparator 接口
 **/

public class ArrayListSort {

    List<Person> returnList() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("张三", 10));
        list.add(new Person("李四", 12));
        list.add(new Person("王五", 13));
        list.add(new Person("赵六", 14));
        list.add(new Person("李雷", 11));
        list.add(new Person("韩梅梅", 8));
        list.add(new Person("jack", 10));
        return list;
    }

    @Test
    public void test() {
        List<Person> returnList = returnList();
//        returnList.sort(new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return 0;
//            }
//        });
        returnList.sort((o1, o2) -> o2.getAge() - o1.getAge());
    }
}



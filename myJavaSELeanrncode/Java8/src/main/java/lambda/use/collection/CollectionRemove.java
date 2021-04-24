package lambda.use.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 11:23
 **/

public class CollectionRemove {

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

        List<Person> list = returnList();
        //删除集合中年龄大于12的元素
        /**
         * 之前迭代器的做法
         * ListIterator<Person> it = list.listIterator();
         * while (it.hasNext()){
         *   Person ele=it.next();
         *   if(ele.age>12){
         *         it.remove();
         *   }
         * }
         */

        list.removeIf(person ->person.getAge()>12 );
//        list.removeIf(new Predicate<Person>() {
//            @Override
//            public boolean test(Person person) {
//                return false;
//            }
//        });

    }

}

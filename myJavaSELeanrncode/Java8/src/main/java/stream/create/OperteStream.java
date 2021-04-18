package stream.create;

import org.junit.Test;
import vo.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/18 15:22
 * Description:
 * stream.createOperteStream
 */
public class OperteStream {

    @Test
    public void test() {
        List<Person> list = getList();

        /**
         *  筛选和切片
         *   filter() --接受Lambda表达式,从流中排除某些元素
         *   limit(n) --截断流,使其元素不超过n
         *   skip(n)  --跳过元素,返回一个跳过前n个元素的流;若流元素不足n个,则返回一个空,与limit()互补\
         *   distinct()--通过元素的hashcode()和equals()方法,去除重复元素;若元素为对象类型,需要重写hashcode()和equasl()方法
         */
//        list.stream().filter(e->e.getAge()>24).forEach(System.out::println);
//        list.stream().filter(e->e.getAge()>24).limit(1).forEach(System.out::println);
//        list.stream().filter(e->e.getAge()>24).skip(2).forEach(System.out::println);
//        list.stream().filter(e->e.getAge()>24).skip(2).distinct().forEach(System.out::println);

        /**
         *  映射
         *  map     --接收lamdba,将元素转换成其他形式或提取信息.接受一个函数作为参数,该函数会被应用到每个元素上,并将其映射称一个新的元素
         *          对函数的要求:入参是元素,要有返回值
         *  flatmap --接收一个函数作为参数,将流中的每个值都替换成另一个流,然后把所有流连接成一个流.
         */
//        list.stream().map(str->str.getAge()).forEach(System.out::println);
//        list.stream().map(Person::getAge).forEach(System.out::println);
//        list.stream().map(this::filters).forEach(stream->stream.forEach(System.out::println));
//        list.stream().flatMap(this::filters).forEach(System.out::println);

        /**
         *  排序
         *  sorted()                 --自然排序   Comparable # int compareTo(T o);
         *  sorted(Comparator com)   --定制排序   int compare(T o1, T o2);
         */
       // list.stream().sorted((e1,e2)->e2.getAge()-e1.getAge()).forEach(System.out::println);

        /**
         * 终止操作:查找与匹配
         *  allMatch     --检查是否匹配所有元素(所有的都要满足条件)
         *  anyMatch     --检查是否至少匹配一个元素(有一个满足条件就行)
         *  noneMatch    --检查是否没有匹配所有元素(所有的都不满足条件)
         *  findFirst    --返回第一个元素
         *  findAny      --返回当前流中的任意元素
         *  count        --返回流中元素的总个数
         *  max          --返回流中最大值
         *  min          --返回流中最小值
         */
//        boolean b = list.stream().allMatch(e -> e.getAge() > 0);
//        boolean b1 = list.stream().anyMatch(e -> e.getAge() > 25);
//        boolean b2 = list.stream().noneMatch(e -> e.getAge() > 26);
//        Person person = list.get(0);
//        Optional<Person> first = list.stream().findFirst();
//        Person person1 = first.get();
//        Optional<Person> any = list.stream().findAny();
//        int size = list.size();
//        long count = list.stream().count();
//        Optional<Person> max = list.stream().max((e1, e2) -> e1.getAge() - e2.getAge());
//        Optional<Person> min = list.stream().min((e1, e2) -> e1.getAge() - e2.getAge());

        /**
         *  规约:可以将流中元素反复结合起来,得到一个值 ,T t代表起始值,不传默认从第一个开始
         *  注意:map和reduce统称为map-reduce模式,因Google用它来进行网络搜索而出名
         *  reduce (T t,BinaryOperator bo)
         *  reduce (BinaryOperator bo)
         */
//        Optional<Person> sunqi = list.stream().reduce((x, y) -> {
//            x.setAge(x.getAge() + y.getAge());
//            return x;
//        });
//        System.out.println(sunqi.get());

        /**
         *  收集
         *  collect  --将流转化为其他形式,接收一个Collector接口的实现,用于给Stream中元素做汇总
         *  Collectors实用类提供了很多静态方法,可以方便的创建收集器实例
         */
        list.stream().map(Person::getAge).collect(Collectors.toList()).stream().forEach(System.out::println);
        //规约到指定集合
        HashSet<String> hashSet = list.stream().map(Person::getName).collect(Collectors.toCollection(HashSet::new));
        //总和
        Double aDouble = list.stream().collect(Collectors.summingDouble(Person::getAge));
        //平均值
        Double aDouble1 = list.stream().collect(Collectors.averagingDouble(Person::getAge));
        //分组
        Map<String, List<Person>> collect = list.stream().collect(Collectors.groupingBy(Person::getName));
        //多级分组
        Map<Integer, Map<String, List<Person>>> collect1 = list.stream().
                collect(Collectors.groupingBy(Person::getAge, Collectors.groupingBy(Person::getName)));
        Map<Boolean, Map<Boolean, List<Person>>> collect2 = list.stream().
                collect(Collectors.groupingBy(e -> e.getName().length() > 5, Collectors.groupingBy(e -> e.getAge() > 26)));
        //分区:满足条件的在一个区.不满足条件的在一个区
        Map<Boolean, List<Person>> collect3 = list.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 26));
        //多级分区
        list.stream().collect(Collectors.partitioningBy(e->e.getAge()>26,Collectors.partitioningBy(e->e.getAge()>36)));

        DoubleSummaryStatistics collect4 = list.stream().collect(Collectors.summarizingDouble(Person::getAge));
        collect4.getAverage();
        collect4.getCount();
        collect4.getMin();
        collect4.getMax();
        collect4.getSum();

        //连接起来
        list.stream().map(Person::getName).collect(Collectors.joining());
        //连接起来,中间加上字符串
        list.stream().map(Person::getName).collect(Collectors.joining(","));
        //连接起来,中间加上字符串,首尾也加上东西
        list.stream().map(Person::getName).collect(Collectors.joining(",","----------","----"));

    }

    public Stream<Person> filters(Person person){
        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(person);
        return arrayList.stream();
    }

    public List<Person> getList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("zhangsan", 23));
        list.add(new Person("lisi", 24));
        list.add(new Person("wangwu", 25));
        list.add(new Person("zhaoliu", 26));
        return list;

    }
}

package jqk.learn.test;

import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import jqk.learn.utils.BeanCoverUtil;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * @Author:JQK
 * @Date:2020/12/15 15:29
 * Package+ClassName:jqk.learn  Test
 **/

public class Test {

    public static void main(String[] args) {
        //print();
        //buildDirectory();
        //testGc();
        // testUtils();
        list();
    }

    public static void print() {
        String temp = "12345,23235235,2352352,23523523,235235,6666,";
        temp = temp.substring(0, temp.length() - 1);
        String[] splitStrings = temp.split(",");
        Arrays.stream(splitStrings).forEach(System.out::println);
    }

    /**
     * 生成md文档目录
     */
    public static void buildDirectory() {
        AtxMarkdownToc.newInstance().genTocFile("D:\\Mysofts\\programs\\Idea " +
                "programs\\my\\javaLearnSE\\myJavaSELeanrncode\\Documents\\分布式事务.md");
    }

    /**
     * -Xmx8M  -XX:+PrintGCDetails -verbose:gc
     * 测试垃圾回收器
     */
    public static void testGc() {
        int i = 0;
        try {
            List<String> list = new ArrayList<>(10);
            String a = "hello";
            while (true) {
                list.add(a); // hello, hellohello, hellohellohellohello ...
                a = a + a;  // hellohellohellohello
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(i);
        }
    }

    /**
     * 测试 List<A> a 转化成 List<B> b
     */
    public static void testUtils() {
        List<User> users = BeanCoverUtil.coverList(returnPersons(), User.class);
        System.out.println(users);
        testCollectingAndThen(users);
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if ("李四".equals(next.getName())) {
                iterator.remove();
                System.out.println(users);
            }
        }
        System.out.println(users);
    }

    public static List<Person> returnPersons() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("1", "张三"));
        list.add(new Person("2", "李四"));
        list.add(new Person("2", "李四"));
        list.add(new Person("1", "张三"));
        Person person = new Person("3", "王五");
        list.add(person);
        return list;
    }

    /**
     * 测试包装类和普通类的区别
     */
    public static void testPackaging() {
        Integer i = 1;
        Boolean b = false;
    }

    /**
     * 测试 users 集合去重并组成新的集合
     */
    public static void testCollectingAndThen(List<User> users) {
        ArrayList<User> collect =
                users.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(User::getName))),
                        ArrayList::new));
        System.out.println(collect);
    }

    public static void list() {
        Person person = new Person("1", "张三");
        Person p = BeanCoverUtil.coverBean(person, Person.class);
        person.setId("2");
        List<Person> list = new ArrayList<>();
        list.add(person);
        p.setId("3");
        list.add(p);
        list.forEach(System.out::println);
    }


}



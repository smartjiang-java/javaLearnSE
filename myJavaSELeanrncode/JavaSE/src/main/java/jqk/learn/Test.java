package jqk.learn;


import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import jqk.learn.utils.BeanConverUtil;
import lombok.Data;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        testutils();
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
            List<String> list = new ArrayList<>();
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
    public static void testutils() {
        List<Person>  list=new ArrayList<>();
        list.add(new Person("1","zhsnagsan"));
        list.add(new Person("2","lisi"));
        List<User> users = BeanConverUtil.converList(list, User.class);
        users.forEach(System.out::println);
    }

}



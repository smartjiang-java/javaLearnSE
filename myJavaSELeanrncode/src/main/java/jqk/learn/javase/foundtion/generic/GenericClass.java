package jqk.learn.javase.foundtion.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型方法
 * @Date:2020/10/12
 * @Author:JQK
 **/

public class GenericClass<T> {

    @Test
    public void mtTest() {

        genericMethod(11);
        genericMethod("11");
        genericMethod(11.1);

        genericMethodUpperlimit(11);
        genericMethodUpperlimit(11.1f);
        genericMethodUpperlimit(11L);


        //理式代换：父类出现的地方，子类也能出现
        ArrayList<Number> list = new ArrayList<>();
        list.add(1);
        //Number->Object->Number
        Number number = list.get(0);
        ArrayList<?> list2 = list;
        //list2.add("3333");   不允许add
        //Objecct
        Object asa = list2.get(0);

    }

    /*这里的参数t就是根据传进来的参数来决定类型*/
    public static <T> void genericMethod(T t) {

        System.out.println(t);
    }

    /*使用方法泛型上限，只能接收Number及其子类类型*/
    public static <T extends Number> void genericMethodUpperlimit(T t) {
        System.out.println(t);
    }

    public static void notGenericMethod(GenericClass<?> genericClass) {
        System.out.println();
    }

    public static void notGenericMethod1(GenericClass<? super Integer> genericClass) {
        System.out.println();
    }

    public static void notGenericMethod2(GenericClass<? extends Number> genericClass) {
        System.out.println();
    }

    //指定上限，存放的都是儿子
    //存放的是？类型，？类型，存放那个的是Number的子类
    //add() Number的子类，list不确定的是什么类型
    public static void say(List<? extends Number> list) {
        //list.add(1);
        //取到的都是Number类型
        Number number = list.get(1);
    }

    //存放那个的都是爸爸
    //存放的是？类型，指定下限是Number，存放的都是Nubmuer的父类
    //add() Number的子类，上转型为Number
    public static void say2(List<? super Integer> list) {
        list.add(1);
        //取出来的都是object类型
        Object object = list.get(0);
    }

}



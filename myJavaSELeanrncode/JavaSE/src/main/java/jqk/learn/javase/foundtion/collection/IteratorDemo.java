package jqk.learn.javase.foundtion.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author:JQK
 * @Date:2020/12/23 9:56
 * Package+ClassName:jqk.learn.javase.foundtion.collection
 **/

public class IteratorDemo {

    /**
     * 使用迭代器遍历并且移除元素
     *
     * @param args main函数参数
     */
    public static void main(String[] args) {
        iterator();
    }

    public static void iterator() {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        //生成iterator的快捷键itco     增强for循环快捷键iter
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer item = iterator.next();
            if (item == 2) {
                iterator.remove();
            }
        }
        System.out.println(list);

    }

}

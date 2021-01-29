package jqk.learn.javase.foundtion.generic;

import java.util.ArrayList;


class GenericCollectiond {
    /*理式代换：父类出现的地方，子类也能出现*/
    public static void print() {
        ArrayList<Number> list = new ArrayList<>();
        list.add(1);
        Number number = list.get(0);   //Number->Object->Number
        ArrayList<?> list2 = list;
        //list2.add("3333");   不允许add
        Object asa = list2.get(0);     //Objecct
    }
}




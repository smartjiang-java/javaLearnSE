package jqk.learn.test;

import lombok.Data;

/**
 * @Author:jiangqikun
 * @Date:2021/5/21 18:00
 **/
@Data
public class Person {

    private String id;

    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }
}

package jqk.learn.framework.spring.ioc;

import lombok.Data;

/**
 * @Author:JiangQiKun
 * @Date:2021/2/21 18:51
 * Description:
 * jqk.learn.framework.spring.iocPerson
 */

@Data
public class Person {

    private  String name;
    private  Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}

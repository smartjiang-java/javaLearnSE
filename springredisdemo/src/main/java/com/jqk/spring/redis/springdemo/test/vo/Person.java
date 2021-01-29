package com.jqk.spring.redis.springdemo.test.vo;

/**
 * @Author:JQK
 * @Date:2021/1/29 19:36
 * @Package:com.jqk.spring.redis.springdemo.test.vo
 * @ClassName:Person
 **/

public class Person {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

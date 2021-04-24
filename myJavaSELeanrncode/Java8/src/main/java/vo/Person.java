package vo;

import lombok.Data;

/**
 * @Author:JiangQiKun
 * @Date:2021/4/23 1:43
 * Description:
 * voPerson
 */
@Data
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
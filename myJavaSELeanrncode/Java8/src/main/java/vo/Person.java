package vo;

import lombok.Data;

/**
 * @Author:jiangqikun
 * @Date:2021/4/15 11:11
 **/

@Data
public
class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
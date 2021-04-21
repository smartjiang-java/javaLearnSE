package jqk.learn.spring.ioc;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author:JQK
 * @Date:2021/3/2 16:22
 * @Package+@ClassName:jqk.learn.framework.spring.core.v1 Person
 **/
@Data
public class Person implements FactoryBean {

    private String name;
    private Integer age;

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package jqk.learn.framework.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring aop test
 *
 * @author 16770
 */

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/core/applicationContext.xml");
        //实现了FactoryBean，创建实例的时候调用FactoryBean接口的getObject()方法;加上&地址符，返回的是对象本身
        if (context.containsBean("person")) {
            Person person = context.getBean("&person", Person.class);
            System.out.println(person);
            //Person person1 = context.getBean("person", Person.class);
        }

        /**
         * spring是个容器，管理所有的bean，bean通过xml文件读取
         * 问题1：既然是spring来管理bean，那么bean肯定有生命周期       控制对象：可以使用代理模式
         * 问题2：在创建bean和销毁bean的时候，spring是不是可以干点啥   增强对象:装饰者模式
         * 问题3；spring如何管理bean与bean之间的依赖关系
         */
    }
}



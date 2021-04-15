package jqk.learn.framework.spring.aop.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring aop test
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/app_auto.xml");
        Tank t = context.getBean(Tank.class);
        t.move();
    }
}

package jqk.learn.framework.spring.aop.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring aop test
 * @author 16770
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/app.xml");
        Tank t = (Tank)context.getBean("tank");
        t.move();
    }
}



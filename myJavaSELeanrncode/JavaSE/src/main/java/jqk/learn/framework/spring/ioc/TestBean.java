package jqk.learn.framework.spring.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:JiangQiKun
 * @Date:2021/2/21 18:53
 * Description:
 * jqk.learn.framework.spring.iocTestBean
 */
public class TestBean {

    public static void main(String[] args) {
      ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/ioc/applicationContext.xml");
        //FileSystemXmlApplicationContext applicationContext1=new FileSystemXmlApplicationContext("myJavaSELeanrncode/applicationContext.xml");
       Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

    }
}

package jqk.learn.javase.foundtion.exception;


import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Author:JQK
 * @Date:2021/3/7 13:24
 **/

public class TestException {

    @Test
    public void myTest(){
        start();
    }

    public static void start(){
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 NoUniqueBeanDefinitionExceptionDemo 作为配置类（Configuration Class）
        applicationContext.register(TestException.class);
        // 启动应用上下文
        applicationContext.refresh();
        ExceptionUtils.printRuntimeException(()-> applicationContext.getBean(String.class));
        ExceptionUtils.printRuntimeException(()-> applicationContext.getBean(String.class),String.class);
        // 关闭应用上下文
        applicationContext.close();
    }

    /**
     * 当没有显示的写出Bean名称，方法名就是bean名称
     */
    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }

}

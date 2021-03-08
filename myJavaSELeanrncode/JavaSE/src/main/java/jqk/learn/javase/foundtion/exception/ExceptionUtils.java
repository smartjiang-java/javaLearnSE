package jqk.learn.javase.foundtion.exception;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;

/**
 * Exception 工具类
 *
 * @Author:JQK
 * @Date:2021/3/7 13:21
 **/

public class ExceptionUtils {

    public static void printRuntimeException(Runnable runnable, Class clazz) {
        try {
            runnable.run();
        } catch (NoUniqueBeanDefinitionException e) {
            if(clazz!=null){
                System.err.printf(" Spring 应用上下文存在%d个 %s 类型的 Bean，具体原因：%s%n",
                        e.getNumberOfBeansFound(), clazz, e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public static void printRuntimeException(Runnable runnable) {
        printRuntimeException(runnable, null);
    }
}

package jqk.learn.spring.annocation;

import java.lang.annotation.*;

/**
 * 注解：笔记
 * 在Java中，有四个元注解(注解的注解，用来表明一个注解的用法)
 *
 * @Target:指明这个注解能用在哪里[]数组
 * @Retention：指明这个注解在什么地方什么时候起作用
 * @Documented：指明这个注解能形成文档
 * @Inherit:指明这个注解是否能被继承 注解可以在程序中获得，注解依赖于反射
 * @元注解
 * @interface 注解名字{}
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table {
    String value();    //如果是value,且只注入value,在注入的时候可以省略value=
    //String type() default "";   //有默认值，可以不显示注入
    String name();
}

@Table(value = "张三", name = "天下无敌")
public class AnnotationsDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        //通过类加载器去获取注解：相当于一只手，能拿到头上的所有东西
        Class<AnnotationsDemo> clazz = AnnotationsDemo.class;
/*        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            //获取注解的类型:是一个interface
            Class<? extends Annotation> type = annotation.annotationType();
            System.out.println(annotation);
        }*/
        Table annexation = clazz.getAnnotation(Table.class);
        if (annexation != null) {
            //do something
            //System.out.println(table.name());

        }
    }
}

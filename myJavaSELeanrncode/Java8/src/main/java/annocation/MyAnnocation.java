package annocation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @Author:jiangqikun
 * @Date:2021/4/19 10:50
 *
 * 重复注解和类型注解
 **/

@Repeatable(MyAnnocations.class)   //定义重复注解必须加这个
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnocation {

    String value() default "lalal";
}

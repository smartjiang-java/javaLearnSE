package jqk.learn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:jiangqikun
 * @Date:2021/5/21 17:25
 **/

public class BeanConverUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanConverUtil.class);

    /**
     *  List转换
     *  注意：targetClass类需要声明为 public ,且必须要有无参构造器
     */
    public static <T> List<T> converList(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList) || targetClass == null) {
            return Collections.emptyList();
        }
        return sourceList.stream().map(
                item -> {
                    if (item == null) {
                        return null;
                    }
                    T targetObj;
                    try {
                        targetObj = targetClass.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        logger.error("sourceObj:{},targetClass:{}", item, targetClass, e);
                        return null;
                    }
                    BeanUtils.copyProperties(item, targetObj);
                    return targetObj;
                }
        ).collect(Collectors.toList());

    }

}

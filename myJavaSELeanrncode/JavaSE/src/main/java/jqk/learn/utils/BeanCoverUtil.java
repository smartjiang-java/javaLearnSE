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

public class BeanCoverUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanCoverUtil.class);

    /**
     *  List转换
     *  注意：targetClass类需要声明为 public ,且必须要有无参构造器
     */
    public static <T> List<T> coverList(List<?> sourceList, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sourceList) || targetClass == null) {
            return Collections.emptyList();
        }
        return sourceList.stream().map(item -> BeanCoverUtil.coverBean(item,targetClass)).collect(Collectors.toList());
    }

    /**
     *  Bean转换
     *  注意：targetClass类需要声明为 public ,且必须要有无参构造器
     */
    public static <T> T coverBean(Object src, Class<T> targetClass) {
        if (src == null) {
            return null;
        }
            T targetObj;
            try {
                targetObj = targetClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                logger.error("sourceObj:{},targetClass:{}", src, targetClass, e);
                return null;
            }
            BeanUtils.copyProperties(src, targetObj);
            return targetObj;
        }

    }




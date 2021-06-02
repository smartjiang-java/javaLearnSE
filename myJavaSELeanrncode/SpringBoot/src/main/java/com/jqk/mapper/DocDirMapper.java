package com.jqk.mapper;

import com.jqk.vo.DocDir;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/6/1 15:16
 **/
@Repository
public interface DocDirMapper {

    /**
     * 查询目录及其子孙目录
     */
    DocDir selectById(String id);

    /**
     * 查询子目录
     */
    List<DocDir> selectByParentId(String id);

    /**
     * 根据单证目录查询
     */
}

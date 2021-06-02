package com.jqk.service;

import com.jqk.vo.DocDir;

import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/6/1 15:15
 **/

public interface DocDirService {

    DocDir selectById(String id);

    List<DocDir> selectByParentId(String id);
}

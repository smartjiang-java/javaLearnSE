package com.jqk.service.impl;

import com.jqk.mapper.DocDirMapper;
import com.jqk.service.DocDirService;
import com.jqk.vo.DocDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/6/1 15:16
 **/
@Service
public class DocDirServiceImpl implements DocDirService {

    @Autowired
    private DocDirMapper docDirMapper;

    @Override
    public DocDir selectById(String id) {
        return docDirMapper.selectById(id);
    }

    @Override
    public List<DocDir> selectByParentId(String id) {
        return docDirMapper.selectByParentId(id);
    }


}

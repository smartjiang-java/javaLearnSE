package com.jqk.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author:jiangqikun
 * @Date:2021/6/1 15:11
 **/
@Data
public class DocDir {

    private  String id;

    private String pid;

    private String name;

    private List<DocDir> docDirs;

    private List<Doc> docs;
}

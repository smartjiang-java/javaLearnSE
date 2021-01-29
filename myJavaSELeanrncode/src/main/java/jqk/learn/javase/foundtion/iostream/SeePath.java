package jqk.learn.javase.foundtion.iostream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author:JQK
 * @Date:2020/12/30 11:05
 * @Package:jqk.learn.javase.foundtion.iostream
 * @ClassName:SeePath  查看当前路径
 **/

public class SeePath {

    @Test
    public void seePathTest() throws IOException {
        String path = new File("").getCanonicalPath();
        //当前项目所在路径：工程地址
        System.out.println(path);
    }
}

package jqk.learn.javase.foundtion.reflect;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author:JiangQiKun
 * @Date:2021/1/9 18:40
 * Description:
 * jqk.learn.javase.foundtion.reflectLoadFile
 */
public class LoadFile {

    @Test
    public void test1(){
        //类加载器可以通过流加载配置文件:只能从classpath下加载配置文件，即src目录下的文件，路径是相对路径，从src开始写起
        InputStream resourceAsStream=null;
        Properties properties = new Properties();
        //Properties可以读取流文件
        try {
            resourceAsStream = LoadFile.class.getClassLoader().getResourceAsStream("config/application.properties");
            properties.load(resourceAsStream);
            System.out.println(properties.get("test.jqk"));
            System.out.println(properties.get("card"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        //FileInputStream可以从任意路径加载配置文件，文件路径是绝对路径
        String path="E:\\java资源全套\\program\\idea program\\JavaSE\\src\\main\\resources\\config\\application.properties";
        FileInputStream fileInputStream = new FileInputStream(path);
        Properties properties=new Properties();
        properties.load(fileInputStream);
        System.out.println( properties.get("card"));

    }
}

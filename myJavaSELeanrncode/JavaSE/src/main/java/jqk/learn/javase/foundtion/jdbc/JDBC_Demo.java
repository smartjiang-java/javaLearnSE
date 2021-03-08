package jqk.learn.javase.foundtion.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author 黄俊 连接数据库,通过表生成实体
 * @Date 2017年10月7日 JDBC:这是用来干嘛的？answer：使用jdbc提供一个一对多的关系。 数据库：SQL
 * SERVER,ORCALE,MYSQL,DB2，MARIDB,DERBY.... 设计思路： Connection接口：提供连接数据库的操作
 * 根据OOP的原则：接口隔离 Statement,PrepareStatement,ResultSet
 * http://www.baidu.com/xx/xx/xx
 */
public class JDBC_Demo {

    public static void main(String[] args) throws Exception {
        /**
         * 一、反射是什么？就是为了在运行时能够获取到本身类信息的一种技术 还可以动态加载类（在运行一个程序的时候，允许动态的加载类进入JVM）
         * 反射机制它的核心点在哪？？？？-》》》》自然而然想到一个对象：在类加载进入JVM并且已经经过加载，验证，准备，解析
         * 初始化<clinit>后的Class对象（这个对象我讲过代表了一个类本身）。那么拿到这个对象 我就能获取到类的所有信息。
         * 那么怎么拿？1.对象.getClass()2.类名.class 3.4. TYPE = (Class<Integer>)
         * Class.getPrimitiveClass("int");
         *
         *
         * 二、JAVA的类加载机制 怎样保证加载的类的安全性？ 你不可能 让危害计算机的代码 也就是类
         * 进入JVM导致用户信息泄露或者信息被删除。。。。恶意操作行为的发生。 所以 java 引入了
         * JAVA的加载机制：采用双亲委派机制和JAVA安全沙箱来保证JVM的安全 双亲委派：Bootstrap Classloader,Ext
         * Classloader,System Classloader,用户自定义
         */
        Class.forName("com.mysql.jdbc.Driver");// 这个方法加载一个类返回一个类的实例对象：class
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "admin");
        /**
         * 我拿到一个连接之后 我是不是可以干事情了？ ORM框架是什么、？ OBJECT RELATION MAPPING对象关系映射
         * 也就是原来我们的数据 在关系型数据库中，是以表的形式存储。 那么我们的对象可不可以把它和表对应起来？？ 表：行和列 对象：属性和方法。
         * 行<-->对象对应 列<-->对象属性对应 1.找到数据库 2.找到表 3.读取表的列信息 4.根据列信息构建类属性
         */
        DatabaseMetaData metaData = connection.getMetaData();// 获取数据库的原信息
        ResultSet tables = metaData.getTables("user", null, null, null);

        //创建包
        String packageName = "org.com.PO";
        String createPath = Utils.createDir(packageName);

        /**
         * Exception in thread "main" java.sql.SQLException: Column Index out of
         * range, 0 < 1. 通过这里我们可以看到对于结果集来说必须index的值从1开始
         */
        while (tables.next()) {
            // String string1 = tables.getString(1);//数据库名
            // String string2 = tables.getString(2);//未知
            String tableName = tables.getString(3);// 表名
            // 这里就可以进行创建类了
            /**
             * 创建类: 1、指明输出地点 2、构建类名 3、写入属性 4、封装属性
             */
            // 包装一个方法，来格式化类名:存放文件内容
            StringBuilder contentBuilder = new StringBuilder();
            ResultSet columns = metaData.getColumns("user", "user", tableName, null);
            Map<String, String> dataMap = new HashMap<>();
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                dataMap.put(columnName, dataType);
            }
            /**
             * //private 类型 变量名;+"public class "
             * +Utils.generateClassName(tableName)+"{"+"\n"
             * //contentBuilder.append("private ")
             * System.out.println(columnName+" "+dataType); /** 1、导包 2、构建类信息
             * 3、构建属性 4、封装属性  5·创建包   6.创建java Bean并写入数据
             */
            //创建包名,并空格回车
            contentBuilder.append("package" + packageName + ";" + "\n");

            Set<Entry<String, String>> entrySet = dataMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                String nessaryImport = Utils.IMPORT_PACK_MAP.get(entry.getValue());
                if (nessaryImport != null) {
                    contentBuilder.append(nessaryImport + "\n");
                }
            }
            //创建类名和属性
            String clazzName = Utils.generateClassName(tableName);
            contentBuilder.append("public class " + clazzName + "{" + "\n");
            for (Entry<String, String> entry : entrySet) {
                contentBuilder.append("\t" + "private " + Utils.SQL_TYPE2JAVA_TYPE.get(entry.getValue()) + " "
                        + Utils.generateFieldName(entry.getKey()) + ";" + "\n");
            }
            //Get和Set方法
            for (Entry<String, String> entry : entrySet) {
                //生成set方法
                contentBuilder.append("\t" + "public void set"
                        + Utils.firstWordUp(Utils.generateFieldName(entry.getKey()))
                        + "(" + Utils.SQL_TYPE2JAVA_TYPE.get(entry.getValue()) + " "
                        + Utils.generateFieldName(entry.getKey()) + ") " + "{" + "\n"
                        + "\t\t" + "this." + Utils.generateFieldName(entry.getKey())
                        + " = " + Utils.generateFieldName(entry.getKey())
                        + ";" + "\n" + "\t" + "}" + "\n");
                //生成get方法
                contentBuilder.append("\t" + "public "
                        + Utils.SQL_TYPE2JAVA_TYPE.get(entry.getValue()) + " get"
                        + Utils.firstWordUp(Utils.generateFieldName(entry.getKey()))
                        + " ( ) " + "{" + "\n" + "\t\t" + "return "+"\t\t"
                        + Utils.generateFieldName(entry.getKey()) + ";" +
                        "\n" + "\t" + "}" + "\n");
            }
            contentBuilder.append("}");
            //写入内容
            Utils.writeClazz(createPath + File.separator + clazzName + ".java", contentBuilder.toString());

        }
    }

}

/**
 * @author 黄俊
 * @Date 2017年10月9日 工具类提供在构建JAVA BEAN时使用的方法
 */
class Utils {
    public final static Map<String, String> SQL_TYPE2JAVA_TYPE = new HashMap<>();
    public final static Map<String, String> IMPORT_PACK_MAP = new HashMap<>();

    static {
        SQL_TYPE2JAVA_TYPE.put("INT UNSIGNED", "Integer");
        SQL_TYPE2JAVA_TYPE.put("VARCHAR", "String");
        SQL_TYPE2JAVA_TYPE.put("TIMESTAMP", "Date");
        SQL_TYPE2JAVA_TYPE.put("INT", "Integer");
        SQL_TYPE2JAVA_TYPE.put("BIGINT", "Long");
        SQL_TYPE2JAVA_TYPE.put("TINYINT", "Byte");
        SQL_TYPE2JAVA_TYPE.put("DATETIME", "Date");
        SQL_TYPE2JAVA_TYPE.put("CHAR", "String");
        IMPORT_PACK_MAP.put("DATETIME", "import java.util.Date;");
        IMPORT_PACK_MAP.put("TIMESTAMP", "import java.util.Date;");
    }

    /**
     * @param tableName 表名
     * @return 根据表名生成的类名
     */
    public static String generateClassName(String tableName) {
        String clazzName = "";
        String[] split = tableName.split("_");
        // 在JDK1.8中 字符串用+连接会在底层生成一个StringBuilder来优化
        for (String string : split) {
            clazzName += (string.substring(0, 1).toUpperCase() + string.substring(1, string.length()));
        }
        return clazzName;
    }

    /**
     * 根据列名生成属性名
     *
     * @param columnName 列名
     * @return 属性名
     */
    public static String generateFieldName(String columnName) {
        String fieldName = "";
        String[] split = columnName.split("_");
        // 在JDK1.8中 字符串用+连接会在底层生成一个StringBuilder来优化
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                fieldName += (split[i].substring(0, 1).toLowerCase() + split[i].substring(1, split[i].length()));
            } else {
                fieldName += (split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length()));
            }
        }
        return fieldName;
    }

    /**
     * 根据传进来的包名进行创建包名
     *
     * @param packageName
     */
    public static String createDir(String packageName) {
        String path = null;
        try {
            //将传进来的xxx.xxx.xxx按照.进行切割,并进行拼接
            String rootPath = new File("").getCanonicalPath()
                    + File.separator + "src";
            //使用.切割需要转译，即\\.
            String[] fileNames = packageName.split("\\.");
            for (String string : fileNames) {
                rootPath += File.separator + string;
            }
            File file = new File(rootPath);
            //与mkdir不同的是：缺少的路径会自动创建，而mkdir前几层缺少，直接报错
            file.mkdirs();
            //获取文件路径
            path = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 将生成的PO写到类路径下
     *
     * @param path         文件路径
     * @param clazzContent 文件内容
     */
    public static void writeClazz(String path, String clazzContent) {
        FileOutputStream fileOutputStream = null;
        try {
            //FileOutputStream 从内存对磁盘  参数：接受数据的位置
            fileOutputStream = new FileOutputStream(new File(path));
            //写入一个数组数据，从起点到尾端,写入一部分
            fileOutputStream.write(clazzContent.getBytes());
            //一定要关流，否则可能会导致部分数据存留在缓冲区里面
            //如果没关，在JVM关闭的时候，会将缓冲区数据刷出
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将传过来的字符串首字母变成大写
     *
     * @param string 字符串
     * @return 返回首字母为大写的字符串
     */
    public static String firstWordUp(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }

}
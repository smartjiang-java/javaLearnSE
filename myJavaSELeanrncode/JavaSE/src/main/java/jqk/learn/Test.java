package jqk.learn;


import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;

/**
 * @Author:JQK
 * @Date:2020/12/15 15:29
 * Package+ClassName:jqk.learn  Test
 **/

public class Test {

    public static void main(String[] args) {
//        String temp="12345,23235235,2352352,23523523,235235,6666,";
//        temp=temp.substring(0,temp.length()-1);
//        String[] splitStrings = temp.split(",");
//        for (String s : splitStrings) {
//            System.out.println(s);
//
//        }

        //生成 md 文档目录
        AtxMarkdownToc.newInstance().genTocFile("D:\\Mysofts\\programs\\Idea " +
                "programs\\my\\javaLearnSE\\myJavaSELeanrncode\\Documents\\分布式事务.md");
    }
}
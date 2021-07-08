字符串判空： StringUtils.isEmpty(str);
集合判空：CollectionUtils.isEmpty(list);
字符串替换s： String.format(“hello%sputty”, "world");
定时：@Scheduled(cron = "0 0/30 9-18 ? * 2-6")
bean复制：BeanUtils.copyProperties(item1,item2);   将item1复制到item2，复制共有的字段
将list<>去重 ，需要重写Person类的equala()和hashcode()方法
List<Person> listC = listA.stream().distinct().collect(Collectors.toList());
测试list断言：Assert.assertEquals(ture,list.size()>0);
@ResponseBody和拦截器配合使用，会忽略拦截器，无论拦截器怎么改

```xml
  <!--json与对象转化-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.73</version>
        </dependency>

        <!--alibaba easyexcel-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>2.2.7</version>
        </dependency>

        <!-- mybatis-plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus</artifactId>
            <version>3.2.0</version>
        </dependency>

        <!-- sofa-http -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.12</version>
        </dependency>

        <!--操作excel-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>4.0.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>4.0.1</version>
        </dependency>
        <!--alibaba easyexcel-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>2.2.7</version>
        </dependency>

```
      
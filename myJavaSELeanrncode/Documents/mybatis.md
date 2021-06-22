1：mybatis的简介以及与Hibernate的区别
sql在xml中，与代码分离；不自动生成sql

2：属性和成员变量
有set和get的称为属性,属性是公共的，成员变量是私有的

3：为什么将成员变量定义为包装类型？
包装类型可以为null
包装类型可以用于泛型。因为泛型在编译时会进行类型擦除，最后只保留原始类型，而原始类型只能是 Object 类及其子类,基本类型是个例。

4：xml中parameterType是传过来的类型，一般是实体类的全限定性类名，也可以不指定，框架会自动根据用户执行的SqlSession方法中的参数自动检测，
#{} 中写入的是实体类的属性，底层通过反射机制组装成get（）方法，通过传入的参数把值获取到
resultType属性并非是查询结果集最后的类型，而是每查出一条DB的一条记录，将该记录封装成的对象的记录，一般使用全限定性类名
resultMap是对resultType的增强，完成由字段到属性的映射，达到将查询结果封装成对象的目的。

5：mybatis支持几种事务管理器类型？
jdbc：使用JDBC的事务管理机制，通过Connection.commit()方法提交，通过rollback()回滚。默认将自动提交关闭了，改为了手动提交
managed:由容器来管理事务的整个生命周期，如spring容器

6：什么时候会创建Connection对象？
当我们需要创建SqlSession对象并需要执行sql语句的时候，这时候mybatis会调用datasource对象来创建Connection对象

7：sqlSession的源码分析

8：<selectKey>标签
用于获取新插入的主键值   resultType 指获取主键的类型，keyProperty代表在java类中的属性值，order表示生成的id在insert之前还是之后，mysql
一般是先执行insert，再生成id，是after；oracle相反，是before。不指定的话，会根据所用DBMS，自动选择。
<selectKey resultType="int" keyProperty="id" order="AFTER">
select @@identity
</selectKey>
注意：mysql在insert语句执行后会自动生成该插入记录的主键值，主键值的生成只与insert语句是否执行有关，与最终是否会提交无关。
无论插入操作是提交还是回滚，DB均会分配id，即使发生回滚，这个id也已经被使用过

9：update操作：新生成一个对象，并给一个id，去覆盖对应id上的对象。

10：SQL 中的字符串拼接
使用函数concat()进行拼接， like concat('%' ，#{O} ，'%')
或者是,注意每个中间用空格，不能省略： like '%' #{O} '%'
注意：以上两种是等效的，都是以动态参数的形式出现在SQL中的
或者 使用纯粹的字符串拼接，可能发生sql注入,不建议使用： like '%${value}%'

11: #和$的区别
#是占位符，底层是动态参数，$是字符串拼接符，底层是硬编码，字符串拼接有sql注入以及预编译所导致的执行效率低下问题
动态参数一般是由用户输入的

12：PreparedStatement 和 Statement
前者可防止sql注入，#后台执行使用的是PreparedStatement,而$使用的是Statement
java代码通过JDBC的Statement向DB发送一条sql语句时，DBMS会对sql语句编译后执行；若通过PreparedStatement,DBMS
会首先编译sql语句，然后将编译后结果放入到DBMS的数据库缓存池中再执行。当DBMS再次收到对该数据库操作的sql时，先从
DB缓冲池中查找该语句是否被编译后，若是，直接执行，不是的话先编译，然后将编译结果放入DB缓冲池，在执行。

13：JDBC的加载顺序
加载DB驱动
获取DB连接
获取数据库操作对象  PreparedStatement 和 Statement
定义sql语句
执行DB操作  .executeQuery();
获取结果集  ResultSet
关闭结果集，数据库操作对象，数据库连接

14：mapper的动态代理方式
就是无需程序员实现dao接口，接口是由Mybatis结合映射文件自动生成的动态代理实现的，使用的是jdk的Proxy动态代理
MapperProxyFactory通过反射newInstance创建一个代理实例，代理掉mapper接口，继续数据库操作。
要求：映射文件的namespace属性设置为Dao接口的全类名。映射文件的sql标签的id值与dao接口中的方法名相同

15：动态sql
动态sql,是mybatis提供的各种标签对条件做出判断以实现动态拼接的sql语句。使用的是OGNI表达式
注意 符号替换 ,以及数组和list的遍历

16：延迟加载
mybatis中的延迟加载，也称为懒加载，是指在关联查询时，按照设置延迟规则推迟对关联对象的select查询。
延迟加载可以有效减轻数据库的压力。
注意：Mybatis中的延迟加载是对关联对象的查询由迟延设置，对于主加载对象都是直接执行查询语句的。
mybatis中对关联对象的查询的执行时机分三种：直接加载，侵入式延迟加载，深度延迟加载
直接加载：执行完对主对象的加载，马上执行对关联对象的select查询
侵入式延迟加载：执行完对主对象的加载，一旦当访问主加载对象的时候，马上执行对关联对象的select查询
深度延迟加载：执行完对主对象的加载，访问主加载对象的时候也不加载，只有当真正访问到关联对象的时候，才会去执行对关联对象的select查询
在mybatis的主配置文件中进行配置

```xml
<setting>
   <!--延迟加载总开关 --> 
    <setting name="lazyLoadingEnabled" value="true" />
    
    <--侵入式延迟加载开关 -->
    <setting name="aggressiveLazyLoading" value="false" />
    
    <!-- -
    false false:直接加载
    true  false:深度延迟加载
    true  true :侵入式延迟加载
    ->
</settings>
```

17：缓存
mybatis的一级缓存是基于 PerpetualCache 类的HashMap的本地缓存，起作用域是SqlSession,当一个SqlSession结束后，改SqlSession的一级缓存消失。
mybatis默认一级缓存开启，且不能关闭。 hashMap的key是sql的id+sql的语句，value是从数据库中查询出的结果
增删改操作，无论是否进行提交，sqlSession.commit(),均会清空一级缓存

mybatis的二级缓存是根据映射文件mapper的namespace划分的，相同namespace的mapper查询数据存放在同一个缓存区域。不同namespace下的数据互不干扰。
但是无论是一级缓存还是二级缓存，都是根据namespace进行分别存放的。
不同的是，二级缓存的生命周期与整个应用同步，与sqlSession是否关闭无关。基于 PerpetualCache 类
二级缓存的目的是为了延长改查询结果的保存时间，提高系统性能。
使用：
--要求涉及到的实体类要实现Serializable接口，若改实体存在父类，或其具有域属性，则父类与域属性也要实现序列化接口
--mapper文件中添加<cache>子标签
```xml
<mapper namespace="com.jqk.mapper.DocDirMapper">
    <cache />
    
    <!-- 
    eviction表示逐出策略，存储达到最大值的处理方法，常见策略有FIFO，LRU
    flushInterval表示缓存刷新的时间间隔，单位毫秒，刷新就是清空缓存，一般不指定
    readOnly 表示数据是否只读，默认是false
    size:二级缓存中可以存放最大缓存数量的大小，默认1024个
    -->
    <cache eviction="FIFO" flushInterval="10800000" readOnly="true" size="512"/>
```
Cache Hit Ratio 表示缓存命中率，先查一级缓存，再查二级缓存。
增删改操作，无论是否进行提交，sqlSession.commit(),均会清空一级和二级缓存，将底层的map中的value置为null，但是key值依然存在
```xml
<!-- 设置某个操作不清空缓存-->
<update id="update" flushCache="false" >
```
二级缓存默认开启，想关闭，需要进行设置，有全局关闭和局部关闭。
全局关闭：在mybatis的主配置文件中
```xml
<setting>
<!-- 关闭二级缓存-->
    <setting name="cacheEnabled" value="false" />
</settings>
```
局部关闭：在某个mapper的<select>中设置
```xml
<!--关闭当前查询的二级缓存 -->
    <cache />
    <select id="selectByParentId" resultMap="newsLaterMapper" useCache="false">
        select id,pid,name from doc_dir where pid=#{xxx}
    </select>
    
```

18：通常一个 Xml 映射文件，都会写一个 Dao 接口与之对应，请问，这个 Dao 接口的工作原理是什么？Dao 接口里的方法，参数不同时，方法能重载吗？
Dao 接口，就是人们常说的 Mapper接口，接口的全限名，就是映射文件中的 namespace 的值，接口的方法名，
就是映射文件中MappedStatement的 id 值，接口方法内的参数，就是传递给 sql 的参数。Mapper接口是没有实现类的，
当调用接口方法时，接口全限名+方法名拼接字符串作为 key 值，可唯一定位一个MappedStatement。
Dao 接口里的方法可以重载，但是Mybatis的XML里面的ID不允许重复，使用动态sql可以实现，多个接口对应的映射必须只有一个，否则启动会报错。
Dao 接口的工作原理是 JDK 动态代理，MyBatis 运行时会使用 JDK 动态代理为 Dao 接口生成代理 proxy 对象，
代理对象 proxy 会拦截接口方法，转而执行MappedStatement所代表的 sql，然后将 sql 执行结果返回。

19：MyBatis 的 Xml 映射文件中，不同的 Xml 映射文件，id 是否可以重复？
不同的 Xml 映射文件，如果配置了 namespace，那么 id 可以重复；如果没有配置 namespace，那么 id 不能重复；毕竟 namespace 不是必须的，只是最佳实践而已。

























































































































































































servlet是线程不安全的，因为在使用反射创建对象后再次访问这个servlet，会再次使用同一个实例，只初始化一次
-->那么如何保证它是线程安全的？
1：使用同步方法或者同步代码块   2:避免使用全局变量

servlet的context参数：[为全局servlet共享] xml里面的
servlet上下文创建后不允许再添加过滤器
实现的方式： 
   1：继承HttpServlet类:[模板模式，在父类也就是抽象类中对接口部分方法进行实现,留出一部分方法给子类进行实现]。定义了一个算法也就是
                       模板， 子类只需要实现某个方法即可获得全部算法实现。
   2：实现Servlet接口,并添加DoGet()，DoPost()方法：必须实现接口的全部的方法,不管你是否使用。（这也是接口和抽象类的区别）

[向下转型的必备条件是必须要有向上转型]

面试题目：[servlet的生命周期]
1：通过反射构建出Servlet的实例，也就是间接或直接实现了Servlet的子类。
2：调用构造器，然后调用init()方法。
3：使用service()方法接收任何一个来自客户端的请求,去处理
4: 请求处理完成之后，在servlet被移出Tomcat之前，（即服务器停止之前）会先调用destroy()方法，然后对剩余的资源进行垃圾回收 

子类复写父类的方法，不允许抛出比父类更大的方法，不允许缩小类的访问范围

[transient关键字表示不可以被序列化]

Servlet 接口
    |
    V
GenericServlet抽象类，默认实现Servlet接口
Tomcat调用init()方法传入config对象，不需要config对象的话直接调用init()即可。
ServletContext由tomcat容器实现
    |
    V
HttpServlet 抽象类继承 GenericServlet抽象类，重写了抽象方法，并提供了一些抽象方法
一般都会实现以下方法的之一或者更多 doDelete init  doPut  doPost doGet，一般不实现service，doOptions，doTrace
Servlet是单实例多线程，多个线程持有同一个对象的引用，因此要注意同步。
HEADER-LASTMOD='Last-Modified':是用来做浏览器缓存的

面试题目： [servlet的调用过程]
tomcat调用servlet接口里的service()方法，判断是不是http请求，是的话调用自己的service()方法

为什么get和head会判断是否过期（做缓存），而put，delete,post不会判断？

Tomcat收到浏览器的请求,将http的头部信息进入Request接口的实例中，产生Response接口的实例;
如果servlet接口的实例也就是我们自己定义的的servlet不存在容器中，那么利用反射机制创建servlet然后调用
servle的init()方法传入servletConfig信息，然后带调用service()方法传入生成的request和response,进行调用

ServletRequest接口  
   startAsync():开启异步任务,解决卡顿，因为servlet是单实例多线程的,如果在一个线程中调用service方法，方法内部比如：线程睡眠，
                查询数据等耗时操作。会导致主调用线程发生阻塞，导致每个连接的哭护短发生阻塞。异步任务要结合线程池使用
       |
       V
HttpServletRequest接口

http协议：超文本传输协议
头部： GET /HTTP/1.1 请求方法,请求路径，请求参数   Host请求地址   User-Agent:浏览器型号   Accept:告诉对方我所能接受参数的类型
      Accept-Language:能接受的语言    Accept-Enconding:能接受的解压方式   
      Cookie:第一次访问写入JSEEIONID=XXX，根据这个拿到客户端Session      Connection:在Http1.1出现，保活      
内容体：大部分是html+css+js,少部分是文件,内容会被压缩
MIME:内容体的表现形式
*/* :text/javascript  application/json   image/jpg   text/plain  text/html

面试题目：[POST请求和GET请求的区别]
GET请求的消息头部有限制，普通限制是2K或者4K，GET请求把请求参数放在请求头部;POST是把请求参数编码后放在请求体里面，POST没有大小限制

200/300状态码都是正常的，400是客户端的问题，500是服务端的问题

[在web中有三大域对象：Request ， session ,ServletContext]
Request：一次请求是一次Request,仅在当前请求中有效
Session:服务器会为每个会话创建一个session对象，所以session中的数据可供当前会话中所有servlet共享。
ServletContext：rvletContext在服务器启动时创建，在服务器关闭时销毁，一个JavaWeb应用只创建一个ServletContext对象，
                所有的客户端在访问服务器时都共享同一个ServletContext对象;ServletContext对象一般用于在多个客户端间共享数据时使用;
总结：session域对象与cookie是搭配使用的。在用户第一次访问需要保存状态的服务器时，如果在服务器中保存了状态，那么服务器会在response的
     时候增加一个cookie名字叫做JSESSIONID。当浏览器在访问容器的时候，会携带上这个cookie，当获取状态时，服务器根据这个cookie来获取
     保存的状态。默认时间：30s   这个状态在tomcat正常停止的时候会将session序列化放到磁盘上。当容器再次启动的时候会反序列化回来。
     特殊情况:如果服务进程被kill，也就是非正常停止，将不会保存数据

xml约束  dtd约束：可以进行提示
  渲染引擎先工作
 1：解析dom树    UTF8里面有一个编码叫做BOM


面试题目：[Class.forName()和自定义类加载器的区别]
Class.forName也是通过类加载器去加载的，但是加载完成之后会初始化，也可以使用重载版本进行手动指定是否会进行初始化
而使用类加载器去加载则不会默认进行初始化

查看工程地址： new File("").getCanonicalPath();

Type接口时超接口，object也实现了这个接口

做项目：
1：先做需求分析，做功能模块划分
2：画用例图：描述实体内部之间的关系
3：搭建前端页面
4：

面试题目：[如何减少hash冲突]
1：优化hash算法：算法越精秒，碰撞的可能性就越小  (h=key.hashcode())^(h>>>16)
2：增大Hash表的空间  （n-1）& hash  n时hash表的长度
   为什么n-1?  因为数组的大小从0开始，0到n-1
   0.75是如何出来的？ 扩容阈值为0.75的情况下，节点出现在桶中概率遵循参数平均0.5的泊松分布。当长度大于8是，碰撞概率为千万分之六
   
[初始化数组内所有位置为固定值-1    数组类]
Arrays.fill(数组，-1)；
[输出数字的全部值]
Arrays.toString(数组)；

面试题目：[foreach原理，为什么数组可以foreach？]
实现了Iterable接口

hashcode方法不能复写，==号比较的是hashcode值，再比较equals
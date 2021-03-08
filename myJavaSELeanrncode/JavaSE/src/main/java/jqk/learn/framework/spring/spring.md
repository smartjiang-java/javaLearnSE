                                 spring学习
# 一：spring 总览
1：课前准备
  心态：戒骄戒躁  谨慎豁达  如履薄冰
  方法：基础:夯实基础，了解动态->思考：保持怀疑，验证一切->分析：不拘小节，观其大意->实践：思辩结合，学以致用
  工具：JDK1.8  spring源码5.2.2  IDEA   Maven

2:spring特性总览
  注意：(有)代表Java API有一些实现
   核心特性：IOC容器  Spring事件(有)  资源管理(有)  国际化(有)  校验 数据绑定  类型转换   spring表达式   面向切面编程AOP 
   数据存储：JDBC，关系型数据库mybatis   事务抽象(来源于EJB)   DAO支持   O/R映射    XML编列：相当于JAVA的序列化
   Web技术：
            Web Servlet技术栈(spring1-4)：Spring MVC    WebSocket   SockJS
            Web Reactive技术栈(spring5引入)：Spring WebFlux   WebClient   WebSocket 
       Spring MVC和Spring WebFlux在注解上是一样的，底层发生了变化：前者需要Servlet引擎支撑，后者是netty的web server,
       当然后者是可以运用Servlet的引擎实现的。spring3.0以后对WebSocket支持。spring5.0之前有httpClient,同步的，5.0
       之后WebClient，异步回调
  技术整合：远程调用(同步的，Java的RMI，Hession社区开源，Dubbo基于Hession协议)  JAVA消息服务JMS(异步的,ActiveMQ,不包括Kafka,RocketMQ)   
          JAVA连接架构JCA(资源连接，比如JDBC)    JAVA管理扩展JMX(运维)    JAVA邮件客户端email   
          本地任务(单机，利用JAVA多线程)     本地调度(单机，利用java多线程)    缓存抽象   spring测试

3：spring版本特性

4：spring模块化设计
  spring将自己的jar包细分，例如spring-core,spring-context,使得开发人员可以按需分配，达到最小依赖化的原则

5：spring对java语言特性运用

6：spring对JDK API实践

7：spring对JAVA EE API整合

8：spring编程模型
面向对象编程:面向接口
面向切面编程：动态代理,aop
面向元编程：配置文件和注解
函数驱动：函数式编程方式，reactive,lambda
模块驱动：不同实现重点创造不同模块,物理maven,运行时@Enable注解

9：spring核心价值
生态系统：围绕spring有spring boot,spring cloud,spring Security,Spring data以及其他，spring是一个基础
API抽象设计：
编程模型：
设计思想：
设计模式：FoF 23，专属模式，
用户基础：

10：面试题精选
1:什么是spring Framework?（答案来自官网）
Spring使创建Java企业应用程序变得容易。它提供了在企业环境中使用Java语言所需的一切，并支持Groovy和Kotlin作为JVM上的替代语言，
并且可以根据应用程序的需求灵活地创建多种体系结构。从Spring Framework 5.1开始，Spring需要JDK 8+（Java SE 8+），
并提供对JDK 11 LTS的现成支持。建议将Java SE 8更新60作为Java 8的最低修补程序版本，但通常建议使用最新的修补程序版本。
Spring支持广泛的应用程序场景。在大型企业中，应用程序通常存在很长时间，并且必须在升级周期不受开发人员控制的JDK和应用程序服务器上运行。
其他服务器则可以作为单个jar运行，并且服务器可能嵌入云环境中。还有一些可能是不需要服务器的独立应用程序（例如批处理或集成工作负载）。
Spring是开源的。它拥有一个庞大而活跃的社区，可以根据各种实际用例提供持续的反馈。这帮助Spring在很长一段时间内成功地发展了。

2:spring里面有哪些重要的模块？
spring-core:基础API管理，如资源管理，泛型处理
spring-beans：spring bean相关，如依赖查找，依赖注入
spring-sop：spring aop处理，如动态代理，aop字节码提升
spring-context：事件驱动，注解驱动，模块驱动Enable等
spring-expression:spring表达式语言模块

3:spring Framework的优势和不足是什么？


# 二：重新认识IOC(维基百科)
1：IOC发展简介
  反转控制：可以称为好莱坞原则：导演打电话给演员，而不是演员打电话给导演，don not call us,we will call you

2：IOC主要实现策略
  服务定位模式：
  模板方法模式：
  策略模式：这个体现的不是特别深刻
  spring中主要是这两个:
  依赖注入：设值，构造，接口，属性
  依赖查找：上下文查询

3：IOC容器的职责
  依赖处理：依赖查找（主动），依赖注入（大部分是被动，容器做的）   依赖怎么来的，怎么把它返回给客户端
  生命周期管理：容器，托管的资源（Java beans或其他资源，比如监听器,xml）        
  配置：容器，外部化配置（属性配置，xml配置），托管的资源（Java beans或其他资源，如线程池，tomcat）

4：IOC容器的实现
  Java SE :java beans,JNDI(java命名和目录的服务接口) 
  JAva EE: EJB,Servlet
  开源：spring Framework,Apache Avalon,Google Guice

5：传统IOC容器实现
  Java Beans作为IOC容器
  特性：依赖查找，生命周期管理，配置元信息，事件，自定义，资源管理，持久化 
  规范：JavaBeans:原生规范,BeanContext:管理bean,以及Bean之间的相互依赖关系，双亲委派可以用到这里

6：轻量级IOC实现

7：依赖查找VS依赖注入

8：构造器注入vs设值注入

9：面试题精选

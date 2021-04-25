                                                    SB + SC 架构启动原理

##  

@SpringBootApplication

```java
/**
 表示一个configuration类，该类声明一个或多个@Bean方法，并且还触发auto-configuration和component scanning 。
 这是一个方便注释，等效于声明@Configuration @EnableAutoConfiguration ，@ComponentScan @EnableAutoConfiguration和@ComponentScan
 @ComponentScan 会扫描当前启动类所在文件夹去递归扫描
 @EnableAutoConfiguration 自动配置需要依赖的bean
 */


```
                                                       
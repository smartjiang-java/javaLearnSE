   xml注册的和注解配置的放置在 BeanDefinationRegistry 里面 ，还有环境 Environment
    BeanFactory 去读取 BeanDefinationRegistry 和 Environment里面的信息，中间通过一系列监听器，
      通过反射去创建Bean
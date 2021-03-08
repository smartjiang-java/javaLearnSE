package springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springdemo.test.SpringRedis;

/**
 * @author 16770
 */
@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
        SpringRedis redis = context.getBean(SpringRedis.class);
        redis.testRedis();
        redis.test2Redis();
        redis.test3Redis();
        redis.test4Redis();
/*        redis.test5Redis();
        redis.test6Redis();*/
        redis.test7Redis();
    }

}

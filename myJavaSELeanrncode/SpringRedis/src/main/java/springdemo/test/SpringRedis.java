package springdemo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import springdemo.test.vo.Person;

import java.util.Map;

/**
 * @Author:JQK
 * @Date:2021/1/29 17:03
 * @Package:com.jqk.spring.redis.springdemo.test
 * @ClassName:SpringRedis
 **/

@Component
public class SpringRedis {

    /**
     * @Component 将这个类注入spring容器中
     * 通用操作：存进redis的是乱码
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 导入自定义Template
     */
    @Autowired
    @Qualifier("getTemplate")
    private StringRedisTemplate template;


    /**
     * 使用redis的高阶api:通过的template操作redis,存进去的是乱码
     */
    public void testRedis() {
        redisTemplate.opsForValue().set("hello", "china");
        System.out.println(redisTemplate.opsForValue().get("hello"));
    }

    /**
     * 使用redis的高阶api:StringRedisTemplate操作redis，存进去的字符串非乱码
     */
    public void test2Redis() {
        stringRedisTemplate.opsForValue().set("hello2", "china02");
        System.out.println(stringRedisTemplate.opsForValue().get("hello2"));
    }


    /**
     * 使用redis的低阶api
     */
    public void test3Redis() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.set("hello3".getBytes(), "china3".getBytes());
        String s = new String(connection.get("hello3".getBytes()));
        System.out.println(s);

        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        hash.put("ps", "name", "zhangsan");
        hash.put("ps", "age", "23");
        System.out.println(hash.entries("ps"));
        System.out.println(hash.get("ps", "name"));

        //将对象整体存入redis
        Person person = new Person();
        person.setName("lisi");
        person.setAge(25);
        //设置json序列化器，如果每次能拿到一耳光配制好的，就比较好
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        //第一个参数是ObjectMapper,第二个参数是否使json扁平化
        Jackson2HashMapper hashMapper = new Jackson2HashMapper(objectMapper, false);
        //将对象转化Map对象
        Map<String, Object> map = hashMapper.toHash(person);
        //存入redis
        stringRedisTemplate.opsForHash().putAll("lisi", map);
        System.out.println(stringRedisTemplate.opsForHash().entries("lisi"));
        //Person取出来
        Person getPerson = objectMapper.convertValue(map, Person.class);
        System.out.println(getPerson);

        //有以下四种序列化
/*        stringRedisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        stringRedisTemplate.setKeySerializer();
        stringRedisTemplate.setValueSerializer();*/
    }


    /**
     * 使用redis的低阶api，使用自定义Template
     */
    public void test4Redis() {
        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        hash.put("ps", "name", "zhangsan");
        hash.put("ps", "age", "23");
        System.out.println(hash.entries("ps"));
        System.out.println(hash.get("ps", "name"));

        //将对象整体存入redis
        Person person = new Person();
        person.setName("wangwu");
        person.setAge(25);
        //第一个参数是ObjectMapper,第二个参数是否使json扁平化
        Jackson2HashMapper hashMapper = new Jackson2HashMapper(objectMapper, false);
        //将对象转化Map对象
        Map<String, Object> map = hashMapper.toHash(person);
        //存入redis
        template.opsForHash().putAll("wangwu", map);
        System.out.println(template.opsForHash().entries("wangwu"));
        //Person取出来
        Person getPerson = objectMapper.convertValue(map, Person.class);
        System.out.println(getPerson);
    }


    /**
     * 发布消息
     */
    public void test5Redis() {
        stringRedisTemplate.convertAndSend("ooxx", "hello world");
    }

    /**
     * 接收消息
     */
    public void test6Redis() {
        RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
        //两个参数，第一个是     ，第二个是通道
        connection.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                //拿到消息后
                byte[] body = message.getBody();
                System.out.println(new String(body));
            }
        }, "ooxx".getBytes());
        //时刻监听
        while (true) {

        }
    }

    /**
     * 聊天室
     */
    public void test7Redis() {
        RedisConnection connection = stringRedisTemplate.getConnectionFactory().getConnection();
        //两个参数，第一个是     ，第二个是通道
        connection.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                //不断接收消息
                byte[] body = message.getBody();
                System.out.println(new String(body));
            }
        }, "ooxx".getBytes());
        //三秒钟发一次消息
        while (true) {
            stringRedisTemplate.convertAndSend("ooxx", "hello from myself");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

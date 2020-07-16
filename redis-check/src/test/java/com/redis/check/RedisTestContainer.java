package com.redis.check;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
         , classes={SpringBootTest.class}
        )
@RunWith(SpringRunner.class)
//@ContextConfiguration(initializers = {RedisTestContainer.Initializer.class})
public class RedisTestContainer {

    // private static Logger log = Logger.getLogger(RedisTestContainer.class.getName());
    private static Logger logger = LoggerFactory.getLogger(RedisTestContainer.class);

    private Jedis jedis;
    private static int mappedPort;


    @Autowired
    TestRestTemplate template;

    @ClassRule
    public static GenericContainer redis =
            new GenericContainer(
                     "redis:6.0.5"
//                    "mysql:8.0.20"
                    )
                    .withExposedPorts(6379)
                    .withLogConsumer(new Slf4jLogConsumer(logger));

//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            int port = redis.getFirstMappedPort();
//            TestPropertyValues.of(
//                    "spring.redis.host=" + port
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }

    @Before
    public void init() {
        mappedPort = redis.getFirstMappedPort();

        System.setProperty("spring.redis.host", String.valueOf(mappedPort));
        System.out.println("Redis mappedPort = " + mappedPort);

//        Jedis jedis = new Jedis("localhost");
        jedis = new Jedis("localhost", mappedPort);
    }

    @Test
    public void testThings() {
//        String page = "ppp";
//        String date = "nnn";
//        String key = String.format("page:%s:%s", page, date);

//        long result = 0;
        LocalDateTime start = LocalDateTime.now();

//        String key1 = encode(generateURL());
//        String key2 = encode(generateURL());
        for (int i = 0; i < 100000; i++) {
            // System.out.println("md5Hex = " + md5Hex);
//            jedis.incr(i % 2 == 0 ? key1 : key2);
            jedis.incr(Utils.generateUrlAndEncode());
        }

        System.out.println("Redis DB Size - " + jedis.dbSize());
        System.out.println("Runtime - " + start.until(LocalDateTime.now(), ChronoUnit.SECONDS) + " sec");
        System.out.println("Runtime - " + start.until(LocalDateTime.now(), ChronoUnit.MINUTES) + " min");

//        System.out.println(
//                String.format("page %s had %d accesses in %s", page, result, date)
//        );
    }
}
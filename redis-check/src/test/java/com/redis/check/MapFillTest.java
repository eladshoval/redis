package com.redis.check;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class MapFillTest {

    private Map<String, Integer> urlsMap = new HashMap<>();

    @Test
    public void fillcheck() {
        LocalDateTime start = LocalDateTime.now();
//        for (int i = 0; i < 100 * 1000 * 1000 ; i++) {
        for (int i = 0; i < 1 * 1000 * 1000 ; i++) {
            final String urlEncoded = Utils.generateUrlAndEncode();
            Integer count = urlsMap.get(urlEncoded);
            if (count == null) {
                count = 0;
            }
            urlsMap.put(urlEncoded, ++count);
        }
        System.out.println("Runtime - " + start.until(LocalDateTime.now(), ChronoUnit.SECONDS) + " sec");
        System.out.println("Runtime - " + start.until(LocalDateTime.now(), ChronoUnit.MINUTES) + " min");
    }
}

package com.sangeet.project;

import com.sangeet.project.evictionpolicy.IEvictionPolicy;
import com.sangeet.project.evictionpolicy.LRUEvictionPolicy;
import com.sangeet.project.storage.HashMapBasedStorage;
import com.sangeet.project.storage.IStorage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheImplTest {

    @Test
    public void doTest(){

        IStorage<Integer, Integer> storage = new HashMapBasedStorage<>(2);
        IEvictionPolicy<Integer> evictionPolicy = new LRUEvictionPolicy<>();

        CacheImpl<Integer, Integer> cache = new CacheImpl<>(storage, evictionPolicy);

        List<String> operations = Arrays.asList("put", "put", "get", "put", "get", "put", "get", "get", "get");
        List<List<Integer>> inputs = Arrays.asList(
                Arrays.asList(1, 1),
                Arrays.asList(2, 2),
                Arrays.asList(1),
                Arrays.asList(3, 3),
                Arrays.asList(2),
                Arrays.asList(4, 4),
                Arrays.asList(1),
                Arrays.asList(3),
                Arrays.asList(4)
        );

        for (int i = 0; i < operations.size(); i++) {
            String operation = operations.get(i);
            switch (operation) {
                case "put":
                    int key = inputs.get(i).get(0);
                    int value = inputs.get(i).get(1);
                    cache.put(key, value);
                    System.out.println("Put: " + key + " " + value);
                    break;
                case "get":
                    key = inputs.get(i).get(0);
//                    Exception exception = assertThrows(Exception.class,
//                            () -> cache.get(key));
//                    assertTrue(exception.getMessage().contains("Key not available"));
                    System.out.println("Get: " + cache.get(key));
                    break;
            }
        }

    }

}

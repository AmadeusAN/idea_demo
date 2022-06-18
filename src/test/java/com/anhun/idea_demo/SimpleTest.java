package com.anhun.idea_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SimpleTest {


    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void test() {
        String result = testRestTemplate.getForObject("/str", String.class);

        assertEquals(result, "AN_HUN_");

    }
}



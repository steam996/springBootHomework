package ru.netology.springboothomework;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    static GenericContainer myapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    static GenericContainer prodapp = new GenericContainer("prodapp")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
        myapp.start();
        prodapp.start();
    }

    @Test
    void contextLoadsDev() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:"
                + myapp.getMappedPort(8080) + "/profile", String.class);
        System.out.println(forEntity.getBody());
        Assert.assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void contextLoadsProd() {
        ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:"
                + prodapp.getMappedPort(8081) + "/profile", String.class);
        System.out.println(prodEntity.getBody());
        Assert.assertEquals("Current profile is production", prodEntity.getBody());
    }

}

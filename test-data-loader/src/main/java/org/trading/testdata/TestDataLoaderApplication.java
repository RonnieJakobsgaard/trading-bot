package org.trading.testdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestDataLoaderApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestDataLoaderApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}

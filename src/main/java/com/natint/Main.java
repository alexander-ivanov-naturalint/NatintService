package com.natint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by ivaa on 10/7/2015.
 */
@SpringBootApplication
@ImportResource("scheduler.xml")
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
        Properties properties = new Properties(System.getProperties());
        properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));
        System.setProperties(properties);
    }
}

package ru.opgmap.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.opgmap.reports")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

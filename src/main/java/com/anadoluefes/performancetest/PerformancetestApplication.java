package com.anadoluefes.performancetest;

import com.anadoluefes.performancetest.worker.ExchangeRateWorker;
import com.anadoluefes.performancetest.worker.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PerformancetestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformancetestApplication.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.print("Integration\t\t");
            System.out.print("Sending Date\t\t");
            System.out.print("Response Time\t\t");
            System.out.println("Difference");
            new Thread(new ExchangeRateWorker()).start();
        };
    }
}

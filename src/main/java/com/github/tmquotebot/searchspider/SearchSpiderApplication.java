package com.github.tmquotebot.searchspider;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
@EnableDiscoveryClient
public class SearchSpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchSpiderApplication.class, args);
    }

}

package com.github.tmquotebot.searchspider.service;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuoteJob  {

    @Scheduled(cron = "0/5 * * * * *")
    @SchedulerLock(name = "favqs_schedule")
    public void execute() {
        log.info("Hello ShedLock!");
    }
}

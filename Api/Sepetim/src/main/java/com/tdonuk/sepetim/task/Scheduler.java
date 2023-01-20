package com.tdonuk.sepetim.task;

import com.tdonuk.sepetim.fetcher.AbstractFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class Scheduler {
    @Autowired
    private AbstractFetcher fetcher;

    @Async
    public void task() {

    }
}

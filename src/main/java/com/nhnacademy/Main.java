package com.nhnacademy;


import com.nhnacademy.common.LogCounter;
import com.nhnacademy.thread_pool.Consumer;
import com.nhnacademy.thread_pool.LogQueue;
import com.nhnacademy.thread_pool.Producer;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        LogQueue logQueue = new LogQueue();
        LogCounter logCounter = new LogCounter();
        int consumerCount = 5;

        Producer producer = new Producer(logQueue,100000000,consumerCount);
        producer.start();

        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < consumerCount; i++) {
            Consumer consumer = new Consumer(logQueue, logCounter);
            consumers.add(consumer);
            consumer.start();
        }

        producer.join();

        for (Consumer consumer : consumers) {
            consumer.join();
        }

        long endTime = System.currentTimeMillis();
        log.info("INFO 로그 비율 : {}", logCounter.getInfoCountPercent());
        log.info("WARN 로그 비율 : {}", logCounter.getWarnCountPercent());
        log.info("ERROR 로그 비율 : {}", logCounter.getErrorCountPercent());
        log.info("분석한 로그 총 갯수 : {}", logCounter.getTotalCount());
        log.info("소요 시간 : {}", String.format("%d%s", endTime - startTime, "ms"));
    }
}

package com.nhnacademy.thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogQueue {
    private final int MAX_SIZE = 10000000;
    private BlockingQueue<String> logQueue;


    public LogQueue(){
        this.logQueue = new LinkedBlockingQueue<>(MAX_SIZE);
    }

    public void addLog(String line) throws InterruptedException {
        logQueue.put(line);
    }

    public String getLog() throws InterruptedException {
        return logQueue.take();
    }
}

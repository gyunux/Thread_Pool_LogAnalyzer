package com.nhnacademy.thread_pool;

import java.util.LinkedList;
import java.util.Queue;

public class LogQueue {
    private Queue<String> logQueue;
    private final int MAX_SIZE = 10000000;

    public LogQueue(){
        this.logQueue = new LinkedList<>();
    }

    public synchronized void addLog(String line) throws InterruptedException {
        while (logQueue.size() >= MAX_SIZE) {
            wait();
        }
        logQueue.add(line);
        notifyAll();
    }

    public synchronized String getLog() throws InterruptedException {
        while (logQueue.isEmpty()) {
            wait();
        }
        String line = logQueue.poll();
        notifyAll();
        return line;
    }
}

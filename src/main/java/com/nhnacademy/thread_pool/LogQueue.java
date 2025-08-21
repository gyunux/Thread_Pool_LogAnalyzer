package com.nhnacademy.thread_pool;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogQueue {
    private Queue<Runnable> logQueue;
    private final int MAX_SIZE = 10000000;

    public LogQueue(){
        this.logQueue = new LinkedList<>();
    }

    public synchronized void addTask(Runnable task) throws InterruptedException {
        while (logQueue.size() >= MAX_SIZE) {
            wait();
        }
        logQueue.add(task);
        notifyAll();
    }

    public synchronized Runnable getTask() throws InterruptedException {
        while (logQueue.isEmpty()) {
            wait();
        }
        Runnable task = logQueue.poll();
        notifyAll();
        return task;
    }
}

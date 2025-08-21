package com.nhnacademy.thread_pool;

import java.util.LinkedList;
import java.util.List;

public class LogAnalyzerThreadPool {
    private LogQueue logQueue;
    private List<Thread> workers;
    private volatile boolean isShutdown = false;

    public LogAnalyzerThreadPool(int poolSize) {
        this.logQueue = new LogQueue();
        this.workers = new LinkedList<>();

        for (int i = 0; i < poolSize; i++) {
            Thread worker = new Thread(() -> {
                try {
                    while (true) {
                        Runnable task = logQueue.getTask();

                        if (task == null) {
                            break;
                        }

                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            workers.add(worker);
            worker.start();
        }
    }

    public void execute(Runnable task) throws InterruptedException {
        if (!isShutdown) {
            logQueue.addTask(task);
        } else {
            throw new IllegalStateException("ThreadPool is shut down");
        }
    }

    public void shutdown() throws InterruptedException {
        isShutdown = true;
        synchronized (logQueue) {
            logQueue.notifyAll();
        }
        for (int i = 0; i < workers.size(); i++) {
            logQueue.addTask(null);
        }
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

package com.nhnacademy.thread_pool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Producer extends Thread {
    private LogQueue logQueue;
    private long logPrefix;
    private int consumerCount;
    public Producer(LogQueue logQueue,long logPrefix,int consumerCount){
        this.logQueue = logQueue;
        this.logPrefix = logPrefix;
        this.consumerCount = consumerCount;
    }

    @Override
    public void run(){
        String fileName = logPrefix + "_log_test.log";
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                logQueue.addLog(line);
            }
        } catch(IOException | InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            for (int i = 0; i < consumerCount; i++) {
                try {
                    logQueue.addLog("END_OF_LOGS");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

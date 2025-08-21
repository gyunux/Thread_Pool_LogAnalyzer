package com.nhnacademy.thread_pool;

import com.nhnacademy.common.LogCounter;

public class Consumer extends Thread{
    private LogQueue logQueue;
    private LogCounter logCounter;

    public Consumer(LogQueue logQueue,LogCounter logCounter){
        this.logQueue = logQueue;
        this.logCounter = logCounter;
    }

    @Override
    public void run(){
        while(true){
            String line = null;
            try {
                line = logQueue.getLog();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(line == null){
                break;
            }
            String[] splitLine = line.split(" ");
            if(splitLine[2].equals("INFO")){
                logCounter.plusInfoCount();
            } else if(splitLine[2].equals("WARN")){
                logCounter.plusWarnCount();
            } else{
                logCounter.plusErrorCount();
            }
        }
    }

}

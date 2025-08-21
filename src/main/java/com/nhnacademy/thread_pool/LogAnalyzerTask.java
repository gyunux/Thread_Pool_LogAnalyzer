package com.nhnacademy.thread_pool;

import com.nhnacademy.common.LogCounter;

public class LogAnalyzerTask implements Runnable {
    private String line;
    private LogCounter logCounter;

    public LogAnalyzerTask(String line,LogCounter logCounter){
        this.line = line;
        this.logCounter = logCounter;
    }

    @Override
    public void run(){
        String[] splitLine = line.split(" ");
        if(splitLine[2].equals("INFO")){
            logCounter.plusInfoCount();
        } else if(splitLine[2].equals("WARN")){
            logCounter.plusWarnCount();
        } else {
            logCounter.plusErrorCount();
        }
    }
}

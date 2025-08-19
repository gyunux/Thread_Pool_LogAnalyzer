package com.nhnacademy.loganalysis.multi;

import com.nhnacademy.common.LogCounter;
import java.util.List;

public class MultiThreadLogAnalyzer implements Runnable{
    private final List<String> lines;
    private final LogCounter logCounter;

    public MultiThreadLogAnalyzer(List<String> lines,LogCounter logCounter){
        this.lines = lines;
        this.logCounter = logCounter;
    }

    @Override
    public void run(){
        for(String line : lines){
            String[] splitLogStr = line.split(" ");
            if(splitLogStr[2].equals("INFO")){
                logCounter.plusInfoCount();
            }
            else if(splitLogStr[2].equals("WARN")){
                logCounter.plusWarnCount();
            }
            else{
                logCounter.plusErrorCount();
            }
        }
    }

}

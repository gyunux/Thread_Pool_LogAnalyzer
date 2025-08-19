package com.nhnacademy.common;

import lombok.Getter;

@Getter
public class LogCounter {
    private long infoCount;
    private long warnCount;
    private long errorCount;

    public void plusInfoCount(){
        infoCount++;
    }

    public void plusWarnCount(){
        warnCount++;
    }

    public void plusErrorCount(){
        errorCount++;
    }

    public long getTotalCount(){
        return infoCount + warnCount + errorCount;
    }

    public float getInfoCountPercent(){
        return (float) infoCount / getTotalCount();
    }

    public float getWarnCountPercent(){
        return (float) warnCount / getTotalCount();
    }

    public float getErrorCountPercent(){
        return (float) errorCount / getTotalCount();
    }
}

package com.nhnacademy.common;

import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;

@Getter
public class LogCounter {
    private AtomicLong infoCount = new AtomicLong();
    private AtomicLong warnCount = new AtomicLong();
    private AtomicLong errorCount = new AtomicLong();

    public void plusInfoCount(){
        infoCount.incrementAndGet();
    }

    public void plusWarnCount(){
        warnCount.incrementAndGet();
    }

    public void plusErrorCount(){
        errorCount.incrementAndGet();
    }

    public long getTotalCount(){
        return infoCount.get() + warnCount.get() + errorCount.get();
    }

    public float getInfoCountPercent(){
        return (float) infoCount.get() / getTotalCount();
    }

    public float getWarnCountPercent(){
        return (float) warnCount.get() / getTotalCount();
    }

    public float getErrorCountPercent(){
        return (float) errorCount.get() / getTotalCount();
    }
}
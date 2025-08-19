package com.nhnacademy;


import com.nhnacademy.common.LogCounter;
import com.nhnacademy.loganalysis.multi.LogLoader;
import com.nhnacademy.loganalysis.multi.MultiThreadLogAnalyzer;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import com.nhnacademy.loganalysis.multi.MultiThreadLogAnalyzer;
import com.nhnacademy.common.LogCounter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "100000000_log_test.log";
        LogCounter logCounter = new LogCounter();

        List<String> lines = LogLoader.loadFileToList(fileName);

        List<Thread> threads = new ArrayList<>();
        MultiThreadLogAnalyzer multiThreadLogAnalyzer = new MultiThreadLogAnalyzer(lines, logCounter);

        for(int i = 0; i < 5; i++){
            Thread thread = new Thread(multiThreadLogAnalyzer);
            threads.add(thread);
            thread.start();
        }

        long startTime = System.currentTimeMillis();

        for(Thread thread : threads){
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        log.info("INFO 로그 비율 : {}", logCounter.getInfoCountPercent());
        log.info("WARN 로그 비율 : {}", logCounter.getWarnCountPercent());
        log.info("ERROR 로그 비율 : {}", logCounter.getErrorCountPercent());
        log.info("분석한 로그 총 갯수 : {}", logCounter.getTotalCount());
        log.info("소요 시간 : {}", String.format("%d%s", endTime - startTime, "ms"));
    }
}

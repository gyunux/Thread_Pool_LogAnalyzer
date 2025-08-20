package com.nhnacademy;


import com.nhnacademy.common.LogCounter;
import com.nhnacademy.loganalysis.multi.LogLoader;
import com.nhnacademy.loganalysis.multi.MultiThreadLogAnalyzer;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        String fileName = "10000000_log_test.log";


        List<String> lines = LogLoader.loadFileToList(fileName);

        List<Thread> threads = new ArrayList<>();
        List<LogCounter> logCounters = new ArrayList<>();

        int threadCount = 5;
        int bundleSize = lines.size() / threadCount;
        for(int i = 0; i < threadCount; i++){
            int from = i * bundleSize;
            int to = ((i-1) * bundleSize == from) ? bundleSize : (i + 1) * bundleSize;
            LogCounter logCounter = new LogCounter();
            logCounters.add(logCounter);
            Thread thread = new Thread(new MultiThreadLogAnalyzer(lines.subList(from,to),logCounter));
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads){
            thread.join();
        }

        int totalIntoCount = 0;
        int totalWarnCount = 0;
        int totalErrorCount = 0;
        int totalCount;
        for(int i = 0;i < threadCount ; i++){
            totalIntoCount += (int) logCounters.get(i).getInfoCount().get();
            totalWarnCount += (int) logCounters.get(i).getWarnCount().get();
            totalErrorCount += (int) logCounters.get(i).getErrorCount().get();
        }
        totalCount = totalIntoCount + totalWarnCount + totalErrorCount;
        long endTime = System.currentTimeMillis();
        log.info("INFO 로그 비율 : {}", (float) totalIntoCount / totalCount);
        log.info("WARN 로그 비율 : {}", (float) totalWarnCount / totalCount);
        log.info("ERROR 로그 비율 : {}", (float) totalErrorCount / totalCount);
        log.info("분석한 로그 총 갯수 : {}", totalCount);
        log.info("소요 시간 : {}", String.format("%d%s", endTime - startTime, "ms"));
    }
}

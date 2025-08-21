package com.nhnacademy;


import com.nhnacademy.common.LogCounter;
import com.nhnacademy.thread_pool.LogAnalyzerTask;
import com.nhnacademy.thread_pool.LogAnalyzerThreadPool;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        LogCounter logCounter = new LogCounter();
        int consumerCount = 5;
        LogAnalyzerThreadPool threadPool = new LogAnalyzerThreadPool(consumerCount);

        try (BufferedReader reader = new BufferedReader(new FileReader("10000000_log_test.log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                threadPool.execute(new LogAnalyzerTask(line, logCounter));
            }
        } catch (IOException e) {
            log.error("파일 읽기 오류: {}", e.getMessage());
        } finally {
            threadPool.shutdown();
        }


        long endTime = System.currentTimeMillis();
        log.info("INFO 로그 비율 : {}", logCounter.getInfoCountPercent());
        log.info("WARN 로그 비율 : {}", logCounter.getWarnCountPercent());
        log.info("ERROR 로그 비율 : {}", logCounter.getErrorCountPercent());
        log.info("분석한 로그 총 갯수 : {}", logCounter.getTotalCount());
        log.info("소요 시간 : {}", String.format("%d%s", endTime - startTime, "ms"));
    }
}

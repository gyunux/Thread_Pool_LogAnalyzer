package com.nhnacademy.loganalysis.single;

import com.nhnacademy.common.LogCounter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleThreadLogAnalyzer {

    public static void start() {
        long startTime = System.currentTimeMillis();
        LogCounter logCounter = new LogCounter();
        String fileName = "1000000000_log_test.log";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLogStr = line.split(" ");
                if (splitLogStr[2].equals("INFO")) {
                    logCounter.plusInfoCount();
                } else if (splitLogStr[2].equals("WARN")) {
                    logCounter.plusWarnCount();
                } else {
                    logCounter.plusErrorCount();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        log.info("INFO 로그 비율 : {}", logCounter.getInfoCountPercent());
        log.info("WARN 로그 비율 : {}", logCounter.getWarnCountPercent());
        log.info("ERROR 로그 비율 : {}", logCounter.getErrorCountPercent());
        log.info("분석한 로그 총 갯수 : {}", logCounter.getTotalCount());
        log.info("소요 시간 : {}", String.format("%d%s", endTime - startTime, "ms"));
    }
}

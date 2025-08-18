package com.nhnacademy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LogGenerator {

    public static void main(String[] args) {
        String fileName = "100000000_log_test.log";
        int numberOfLines = 100000000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String[] logLevels = {"INFO", "WARN", "ERROR"};
            String[] messages = {
                    "UserLoggedIn: user",
                    "DownloadFailed: file",
                    "Database connection lost",
                    "API call success",
                    "Login attempt failed"
            };

            for (int i = 0; i < numberOfLines; i++) {
                String timestamp = LocalDateTime.now().format(formatter);
                String logLevel = logLevels[random.nextInt(logLevels.length)];
                String message = messages[random.nextInt(messages.length)];
                String logLine = String.format("%s %s [%s] %s%d\n", timestamp, logLevel, Thread.currentThread().getName(), message, random.nextInt(1000));
                writer.write(logLine);
            }
            System.out.println("로그 파일이 성공적으로 생성되었습니다: " + fileName);
        } catch (IOException e) {
            System.err.println("로그 파일 생성 중 오류 발생: " + e.getMessage());
        }
    }
}

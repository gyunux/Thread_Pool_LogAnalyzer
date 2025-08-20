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
            String line;
            try {
                line = logQueue.getLog();
                // Producer가 보내는 종료 신호(Poison Pill)를 확인합니다.
                if ("END_OF_LOGS".equals(line)) {
                    break;
                }
            } catch (InterruptedException e) {
                // 스레드가 중단되면, 중단 상태를 다시 설정하고 루프를 종료합니다.
                Thread.currentThread().interrupt();
                break;
            }

            try {
                int firstSpace = line.indexOf(' ');
                int secondSpace = line.indexOf(' ', firstSpace + 1);

                // 로그 레벨의 시작 문자를 확인하여 카운트를 증가시킵니다.
                if (secondSpace > 0) {
                    char logLevelChar = line.charAt(secondSpace + 1);
                    if (logLevelChar == 'I') { // INFO
                        logCounter.plusInfoCount();
                    } else if (logLevelChar == 'W') { // WARN
                        logCounter.plusWarnCount();
                    } else { // ERROR
                        logCounter.plusErrorCount();
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                // 잘못된 형식의 로그 라인은 ERROR로 카운트합니다.
                logCounter.plusErrorCount();
            }
        }
    }

}

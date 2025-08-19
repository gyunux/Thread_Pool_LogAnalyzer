package com.nhnacademy.loganalysis.multi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogLoader {
    public static List<String> loadFileToList(String fileName) throws IOException {
        List<String> logLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logLines.add(line);
            }
        }
        return logLines;
    }
}

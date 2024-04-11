package ua.reed.awsproducerapp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.reed.awsproducerapp.dto.FileContent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@Service
public class SimpleFileService {

    private static final String MESSAGES_TXT = "messages.txt";

    @SuppressWarnings("deprecation")
    public void storeFile(final String content) {
        if (StringUtils.isEmpty(content)) {
            throw new IllegalArgumentException("Content must not be null or empty!");
        }
        try (var writer = new BufferedWriter(new PrintWriter(new FileOutputStream(MESSAGES_TXT)))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    public FileContent readFile(final String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name must not be null or empty!");
        }
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            return new FileContent(reader.lines().collect(Collectors.joining()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

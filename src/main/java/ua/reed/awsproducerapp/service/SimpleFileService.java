package ua.reed.awsproducerapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.reed.awsproducerapp.dto.FileContent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class SimpleFileService {

    @Value("${file.path}")
    private String filePath;

    @SuppressWarnings("deprecation")
    public void storeFile(final String content) {
        if (StringUtils.isEmpty(content)) {
            throw new IllegalArgumentException("Content must not be null or empty!");
        }
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, content.concat("\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    public FileContent readFile(final String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name must not be null or empty!");
        }
        try {
            return new FileContent(String.join("\n", Files.readAllLines(Paths.get(fileName))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

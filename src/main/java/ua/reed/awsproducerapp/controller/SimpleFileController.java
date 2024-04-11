package ua.reed.awsproducerapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.reed.awsproducerapp.dto.FileContent;
import ua.reed.awsproducerapp.service.SimpleFileService;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class SimpleFileController {

    private final SimpleFileService fileService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void storeFile(@RequestBody final FileContent request) {
        this.fileService.storeFile(request.content());
    }

    @GetMapping
    public ResponseEntity<FileContent> readFile(@RequestParam("fileName") final String fileName) {
        return ResponseEntity.ok()
                .body(this.fileService.readFile(fileName));
    }
}

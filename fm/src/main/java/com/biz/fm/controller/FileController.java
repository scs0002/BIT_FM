package com.biz.fm.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biz.fm.domain.dto.UploadFileResponse;
import com.biz.fm.service.FileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/file")
@RestController
public class FileController {
	
	private final FileService fileService;
	
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        return fileService.loadFile(fileName);
    }

    @GetMapping("/thumbnail/{fileName}")
    public ResponseEntity<Resource> downloadThumbnail(@PathVariable String fileName) {
        return fileService.loadThumbnail(fileName);
    }

    @PostMapping
    public ResponseEntity<List<UploadFileResponse>> upload(@RequestPart("files") MultipartFile[] files) {
        List<UploadFileResponse> responses = fileService.saveFiles(files);
        return ResponseEntity.ok().body(responses);
    }
}

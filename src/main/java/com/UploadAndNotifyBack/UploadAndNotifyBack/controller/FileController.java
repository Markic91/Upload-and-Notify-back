package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import java.io.IOException;
import java.util.List;
import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import com.UploadAndNotifyBack.UploadAndNotifyBack.service.PostMultipartFiles;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.FileSystemStorageService;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {

    private final FileRepository fileRepository;
    private final PostMultipartFiles postMultipartFiles;
    private final FileSystemStorageService fileSystemStorageService;

    public FileController(FileRepository fileRepository, PostMultipartFiles postMultipartFiles,
            FileSystemStorageService fileSystemStorageService) {
        this.fileRepository = fileRepository;
        this.postMultipartFiles = postMultipartFiles;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> getFiles(@PathVariable String fileName, HttpServletRequest request) throws StorageException, IOException {

        Resource resource = fileSystemStorageService.loadAsResource(fileName);
        String contentType = null;
        
        contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("files")
    public List<MyFile> getFiles() {
        return fileRepository.findAll();
    }

    @PostMapping("/files")
    public List<MyFile> createFiles(@RequestParam("files") List<MultipartFile> files,
            @RequestParam("exp") String exp,
            @RequestParam(required = false, value = "mail") String mail) throws IOException, StorageException {

        return (postMultipartFiles.postMultipartFile(files, exp, mail));
    }
}

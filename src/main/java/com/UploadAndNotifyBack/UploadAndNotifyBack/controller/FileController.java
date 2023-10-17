package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import com.UploadAndNotifyBack.UploadAndNotifyBack.service.PostMultipartFiles;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.FileSystemStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableScheduling
public class FileController {

    private static  FileRepository fileRepository;

    private final PostMultipartFiles postMultipartFiles;
    private final FileSystemStorageService fileSystemStorageService;

    public FileController(FileRepository fileRepository, PostMultipartFiles postMultipartFiles, FileSystemStorageService fileSystemStorageService) {

        this.fileRepository = fileRepository;
        this.postMultipartFiles = postMultipartFiles;
        this.fileSystemStorageService = fileSystemStorageService;

    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> getFiles(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileSystemStorageService.loadAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }


 @GetMapping ("files")
        public List<MyFile> getFiles() {
     return fileRepository.findAll();
 }

    @PostMapping("/files")
    public List<MyFile> createFiles(@RequestParam("files") List<MultipartFile> files,
                                              @RequestParam ("exp") String exp,
                                              @RequestParam (required = false,value ="mail") String mail ) throws  IOException {

        return ( postMultipartFiles.postMultipartFile(files, exp, mail));
    }

    @DeleteMapping("files")
    public static boolean deleteFiles() {
    fileRepository.deleteAll();
    return true;
    }
    @Scheduled(cron = "0 0 02 ? 1-12 MON", zone = "UTC")
    @DeleteMapping("files/expired")
    public static void deleteExpiredFiles() {
        List<MyFile> allFiles = fileRepository.findAll();
        for (MyFile file : allFiles) {
            if (LocalDateTime.now().compareTo(file.getDate()) == 1 && file.getDate() != null) {
                fileRepository.delete(file);
            }
        }
        fileRepository.flush();
    }

}





package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {

    private final FileRepository fileRepository;
    public FileController(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @GetMapping("/files")
    public List<File> getFiles() {
        return fileRepository.findAll();
    }

    @PostMapping("/files")
    public File createFile(@RequestBody File newFile) {
        return fileRepository.save(newFile);
    }
}

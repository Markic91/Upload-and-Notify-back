package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/files")
    public List<File> getFiles() {
        return fileRepository.findAll();
    }

    @PostMapping("/files")
    public File createFile(@RequestBody File newFile) {
        File newFileBis = new File();
            newFileBis.setLink(newFile.getLink());
            newFileBis.setExpiration(newFile.getExpiration());
            if (newFile.getExpiration().equals("1 jour") ) {
                newFileBis.setDate(LocalDateTime.now().plusDays(1));
            }
        if (newFile.getExpiration().equals("1 mois") ) {
            newFileBis.setDate(LocalDateTime.now().plusMonths(1));
        }
        if (newFile.getExpiration().equals("1 an") ) {
            newFileBis.setDate(LocalDateTime.now().plusYears(1));
        }
        if (newFile.getExpiration().equals("jamais") ) {
        }
        return fileRepository.save(newFileBis);
    }
}
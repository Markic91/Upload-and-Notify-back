package com.UploadAndNotifyBack.UploadAndNotifyBack.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;

@Service
public class FileJobs {
    
    private final FileRepository fileRepository;

    public FileJobs(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }
    
    @Scheduled(cron = "0 0 02 ? 1-12 MON", zone = "UTC")
    public void deleteExpiredFiles() {
        List<MyFile> allFiles = this.fileRepository.findAll();
        for (MyFile file : allFiles) {
            if (LocalDateTime.now().compareTo(file.getDate()) == 1 && file.getDate() != null) {
                this.fileRepository.delete(file);
            }
        }
        this.fileRepository.flush();
    }
}

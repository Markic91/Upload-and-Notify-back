package com.UploadAndNotifyBack.UploadAndNotifyBack.service;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.FileSystemStorageService;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostMultipartFiles {

    private final StorageService storageService;
    private final FileSystemStorageService fileSystemStorageService;
    private final FileRepository fileRepository;


    private PostMultipartFiles(StorageService storageService, FileSystemStorageService fileSystemStorageService, FileRepository fileRepository) {
        this.storageService = storageService;
        this.fileSystemStorageService = fileSystemStorageService;
        this.fileRepository = fileRepository;
    }

    public void postMultipartFile(List<MultipartFile> files, String exp, String mail ) throws MalformedURLException {
        ArrayList<MyFile> myNewList = new ArrayList<>();
        for (
                MultipartFile file : files) {
            MyFile myNewFile = new MyFile();
            myNewFile.setName(file.getOriginalFilename());
            storageService.store(file);
            myNewFile.setLink(String.valueOf((fileSystemStorageService.load(file.getOriginalFilename()).toUri().toURL())));
            myNewList.add(myNewFile);
        }

        myNewList.forEach(item -> {
            item.setExpiration(exp);
            switch (exp) {
                case "1 jour" -> item.setDate(LocalDateTime.now(ZoneOffset.UTC).plusDays(1));
                case "1 mois" -> item.setDate(LocalDateTime.now(ZoneOffset.UTC).plusMonths(1));
                case "1 an" -> item.setDate(LocalDateTime.now(ZoneOffset.UTC).plusYears(1));
                case "jamais" -> item.setDate((LocalDateTime.MAX));
            }
        });

        myNewList.forEach(item -> item.setMail(mail));
        if (!mail.equals("null")) {
            EmailService.sendEmail(mail, myNewList);
        }
        fileRepository.saveAll(myNewList);
    }
}

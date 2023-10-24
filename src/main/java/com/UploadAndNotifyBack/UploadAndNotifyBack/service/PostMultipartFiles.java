package com.UploadAndNotifyBack.UploadAndNotifyBack.service;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.FileSystemStorageService;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostMultipartFiles {

    private final FileSystemStorageService storageService;
    private final FileRepository fileRepository;
    private final EmailService emailService;

    private PostMultipartFiles(FileSystemStorageService storageService, FileRepository fileRepository, EmailService emailService) {
        this.storageService = storageService;
        this.fileRepository = fileRepository;
        this.emailService = emailService;
    }

    public ArrayList<MyFile> postMultipartFile(List<MultipartFile> files, String exp, String mail ) throws MalformedURLException, StorageException {
        ArrayList<MyFile> myNewList = new ArrayList<>();
        for ( MultipartFile file : files) {
            MyFile myNewFile = new MyFile();
            myNewFile.setName(file.getOriginalFilename());
            this.storageService.store(file);
            var link = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(Objects.requireNonNull(file.getOriginalFilename()))
                    .toUriString();
            myNewFile.setLink(link);
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

        if (!mail.equals("null")) {
            this.emailService.sendEmail(mail, myNewList);
        }
        fileRepository.saveAll(myNewList);
        return myNewList;
    }
}

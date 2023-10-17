package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.boot.Metadata;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {

    private static  FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

//    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("./Download");

    @GetMapping("/files")
    public List<File> getFiles() {
        return fileRepository.findAll();
    }

    @PostMapping("/files")

    public ResponseEntity<String> createFiles(@RequestParam ("file") List<MultipartFile> files,
                                              @RequestParam ("link") List<String> links,
                                              @RequestParam ("exp") String exp,
                                              @RequestParam (required = false,value ="mail") String mail ) {

        ArrayList<File> myNewList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File myNewFile = new File();
            myNewFile.setName(files.get(i).getOriginalFilename());
            for (String link : links) {
                myNewFile.setLink(links.get(i));
            }
            myNewList.add(myNewFile);
        }

        myNewList.forEach(item -> {item.setExpiration(exp);
            switch (exp){
                case "1 jour":
                    item.setDate(LocalDateTime.now().plusDays(1));
                    break;
                case "1 mois":
                    item.setDate(LocalDateTime.now().plusMonths(1));
                    break;
                case "1 an":
                    item.setDate(LocalDateTime.now().plusYears(1));
                    break;
                case "jamais":
                    break;
            }});

        myNewList.forEach(item -> item.setMail(mail));
        fileRepository.saveAll(myNewList);
        return (new ResponseEntity<>("Successful", HttpStatus.OK));
    }


    @DeleteMapping("files")
    public static boolean deleteFiles() {
    fileRepository.deleteAll();
    return true;
    }

    @DeleteMapping("files/expired")
    public static void deleteExpiredFiles() {
        List<File> allFiles = fileRepository.findAll();
        for (File file : allFiles) {
            if (LocalDateTime.now().compareTo(file.getDate()) == 1 && file.getDate() != null) {
                fileRepository.delete(file);
            }
        }
        fileRepository.flush();
        System.out.println("C'est qui le boss");
    }

}
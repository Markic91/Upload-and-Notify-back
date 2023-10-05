package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    List<String> files = new ArrayList<String>();
    private final Path rootLocation = Paths.get("./Download");

    @GetMapping("/files")
    public List<File> getFiles() {
        return fileRepository.findAll();
    }

    @PostMapping("/files")
    public ResponseEntity<String> createFiles( @RequestParam("file") List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files ) {
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());

        }
        return (new ResponseEntity<>("Successful", HttpStatus.OK));    }
    }
//    public File createFile(@RequestBody File newFile) {
////        File newFileBis = new File();
////            newFileBis.setLink(newFile.getLink());
////            newFileBis.setExpiration(newFile.getExpiration());
////            if (newFile.getExpiration().equals("1 jour") ) {
////                newFileBis.setDate(LocalDateTime.now().plusDays(1));
////            }
////        if (newFile.getExpiration().equals("1 mois") ) {
////            newFileBis.setDate(LocalDateTime.now().plusMonths(1));
////        }
////        if (newFile.getExpiration().equals("1 an") ) {
////            newFileBis.setDate(LocalDateTime.now().plusYears(1));
////        }
////        if (newFile.getExpiration().equals("jamais") ) {
////        }
////        return fileRepository.save(newFileBis);

//}
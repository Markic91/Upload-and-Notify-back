package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import java.io.IOException;
import java.util.List;
import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
<<<<<<< HEAD
import com.UploadAndNotifyBack.UploadAndNotifyBack.service.PostMultipartFiles;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
>>>>>>> 8c960caf2675119544d205f13cc61447558c1f43

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FileController {

    private final FileRepository fileRepository;

    private final PostMultipartFiles postMultipartFiles;

    public FileController(FileRepository fileRepository, PostMultipartFiles postMultipartFiles) {

        this.fileRepository = fileRepository;
        this.postMultipartFiles = postMultipartFiles;
    }

    @GetMapping("/files")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
        public List<MyFile> getFiles() {
       return fileRepository.findAll();
//  }
    }


<<<<<<< HEAD
=======
    @Autowired
    private JavaMailSender javaMailSender;

>>>>>>> 8c960caf2675119544d205f13cc61447558c1f43
    @PostMapping("/files")
    public ResponseEntity<String> createFiles(@RequestParam("files") List<MultipartFile> files,
                                              @RequestParam ("exp") String exp,
                                              @RequestParam (required = false,value ="mail") String mail ) throws  IOException {

<<<<<<< HEAD
        postMultipartFiles.postMultipartFile(files, exp, mail);
        return (new ResponseEntity<>("Success", HttpStatus.OK));
=======
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


        // Envoyer un e-mail
        if (mail != null && !mail.isEmpty()) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

           // pour définir l'@ mail du destinataire'
            mailMessage.setTo(mail);

            mailMessage.setSubject("Notification de partage de fichiers");
            mailMessage.setText("Coucou, vos fichiers ont été partagés avec succès. Voici les liens de téléchargement :");
            // Ajout des liens de téléchargement au texte de l'e-mail
            javaMailSender.send(mailMessage);
        }


        return (new ResponseEntity<>("Successful", HttpStatus.OK));
>>>>>>> 8c960caf2675119544d205f13cc61447558c1f43
    }


    @DeleteMapping("files")
    public boolean deleteFiles() {
    fileRepository.deleteAll();
    return true;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 8c960caf2675119544d205f13cc61447558c1f43

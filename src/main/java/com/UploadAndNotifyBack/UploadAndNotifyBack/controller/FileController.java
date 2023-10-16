package com.UploadAndNotifyBack.UploadAndNotifyBack.controller;

import java.io.IOException;
import java.util.List;
import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import com.UploadAndNotifyBack.UploadAndNotifyBack.service.PostMultipartFiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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



    @PostMapping("/files")
    public ResponseEntity<String> createFiles(@RequestParam("files") List<MultipartFile> files,
                                              @RequestParam ("exp") String exp,
                                              @RequestParam (required = false,value ="mail") String mail ) throws  IOException {

        postMultipartFiles.postMultipartFile(files, exp, mail);
        return (new ResponseEntity<>("Success", HttpStatus.OK));
    }


    @DeleteMapping("files")
    public boolean deleteFiles() {
    fileRepository.deleteAll();
    return true;
    }

}




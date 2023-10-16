package com.UploadAndNotifyBack.UploadAndNotifyBack.entity;


import jakarta.persistence.*;

import java.nio.file.Path;
import java.time.LocalDateTime;

@Entity
@Table(name = "Files")
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String link;
    private String expiration;
//    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime date ;

    private String mail;



    public MyFile() {}

    public MyFile(String originalFilename) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}

package com.UploadAndNotifyBack.UploadAndNotifyBack.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
//@Table(indexes = @Index(name = "expiration_index", columnList = "expiration"))

@Table(name = "Files")
public class MyFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String link;
    private String expiration;

    private LocalDateTime date ;


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

}

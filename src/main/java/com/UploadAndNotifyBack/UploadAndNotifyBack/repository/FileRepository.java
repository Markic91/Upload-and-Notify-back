package com.UploadAndNotifyBack.UploadAndNotifyBack.repository;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileRepository extends JpaRepository<MyFile, Long> {
}

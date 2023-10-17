package com.UploadAndNotifyBack.UploadAndNotifyBack.repository;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}

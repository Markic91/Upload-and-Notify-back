package com.UploadAndNotifyBack.UploadAndNotifyBack;

import com.UploadAndNotifyBack.UploadAndNotifyBack.controller.FileController;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.FileSystemStorageService;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class UploadAndNotifyBackApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UploadAndNotifyBackApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}

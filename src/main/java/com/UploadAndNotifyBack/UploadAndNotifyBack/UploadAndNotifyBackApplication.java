package com.UploadAndNotifyBack.UploadAndNotifyBack;

import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageProperties;
import com.UploadAndNotifyBack.UploadAndNotifyBack.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)

public class UploadAndNotifyBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(UploadAndNotifyBackApplication.class, args);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		calendar.set(Calendar.HOUR_OF_DAY, 14);
		calendar.set(Calendar.MINUTE, 18);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();
		Timer timer = new Timer();
		timer.schedule(new TimerSet(), time);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}

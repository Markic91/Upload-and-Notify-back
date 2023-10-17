package com.UploadAndNotifyBack.UploadAndNotifyBack;

import com.UploadAndNotifyBack.UploadAndNotifyBack.entity.File;
import com.UploadAndNotifyBack.UploadAndNotifyBack.repository.FileRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@SpringBootApplication
public class UploadAndNotifyBackApplication {

//	private static FileRepository fileRepository;
//
//	public UploadAndNotifyBackApplication(FileRepository fileRepository) {
//		this.fileRepository = fileRepository;
//	}


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
}

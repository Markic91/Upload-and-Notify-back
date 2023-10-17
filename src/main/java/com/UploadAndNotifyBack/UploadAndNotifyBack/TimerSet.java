package com.UploadAndNotifyBack.UploadAndNotifyBack;

import com.UploadAndNotifyBack.UploadAndNotifyBack.controller.FileController;

import java.util.TimerTask;

public class TimerSet extends TimerTask {

    @Override
    public void run() {
        FileController.deleteExpiredFiles();
    }

}
package com.lms.util;

import javax.swing.*;
import java.util.List;

public class FileUploadWorker extends SwingWorker<Void, Integer> {
    private JProgressBar progressBar;
    private JButton buttonToEnable;

    public FileUploadWorker(JProgressBar progressBar, JButton buttonToEnable) {
        this.progressBar = progressBar;
        this.buttonToEnable = buttonToEnable;
    }

    @Override
    protected Void doInBackground() throws Exception {
        for (int i = 0; i <= 100; i += 10) {
            Thread.sleep(200); // Simulate network delay
            publish(i);
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        for (int value : chunks) {
            progressBar.setValue(value);
        }
    }

    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, "File Uploaded Successfully!");
        buttonToEnable.setEnabled(true);
        progressBar.setValue(0);
    }
}

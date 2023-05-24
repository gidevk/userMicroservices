package com.expriment.service;
import java.io.IOException;

public class YouTubeDownloader {

    public static void downloadVideo(String videoUrl) {
        try {
            // Set the command to execute
            String[] command = {"youtube-dl", "-f", "best", videoUrl};

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Video downloaded successfully!");
            } else {
                System.out.println("Error downloading video!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        String videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
//        downloadVideo(videoUrl);
//    }
}

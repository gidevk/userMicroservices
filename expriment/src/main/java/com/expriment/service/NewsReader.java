package com.expriment.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NewsReader {

    public static void readLatestNews() {
        try {
            // Set the API endpoint URL
//            Note that you need to sign up for the News API and obtain an API key before using this code. You can sign up for a free account at https://newsapi.org/.
//            api key=5ad6a4056013416da1b56ab6d933b73d
            String apiUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=5ad6a4056013416da1b56ab6d933b73d";

            // Create a URL object and open a connection to the API endpoint
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response from the API
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Print the headlines to the console
            System.out.println("Latest news headlines:");
            System.out.println("-----------------------");
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        readLatestNews();
//    }
}


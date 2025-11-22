package com.lms.service;

import com.google.gson.Gson;
import com.lms.model.Quote;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class QuoteService {
    private static final String API_URL = "https://api.quotable.io/random";

    public CompletableFuture<Quote> fetchRandomQuote() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line);
                }
                conn.disconnect();

                Gson gson = new Gson();
                return gson.fromJson(output.toString(), Quote.class);
            } catch (Exception e) {
                // Fallback quote in case of error (offline, API down, etc.)
                Quote fallback = new Quote();
                // Using reflection or a constructor would be cleaner, but for simple JSON
                // model:
                // We can just return a dummy object or handle it.
                // Let's just return null and handle in UI, or return a specific error quote.
                System.err.println("Error fetching quote: " + e.getMessage());
                return null;
            }
        });
    }
}

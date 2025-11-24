package me.zunynz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Translator {
    private static String SCRIPT_URL =
            "https://script.google.com/URL"; // Enter the URL to script here
    private static String SOURCE_LANG = "en"; // Write here the language code of the original text

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.print("Write your text: ");
            String text = scanner.nextLine();

            System.out.print("The amount of iterations: ");
            int iterationsNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the languages separated by commas (en, ru, de): ");
            String line = scanner.nextLine();
            String[] languages = line.split(",\\s*");

            String currentText = text;

            for (int i = 1; i <= iterationsNumber; i++) {
                System.out.println("=== Iteration " + i + " ===");
                String prevLang = SOURCE_LANG;
                for (String lang : languages) {
                    if (!lang.equalsIgnoreCase(prevLang)) {
                        try {
                            currentText = translate(prevLang, lang, currentText);
                            System.out.println("Translated from " + prevLang + "to " + lang + ": " + currentText);
                            prevLang = lang;
                        } catch (IOException e) {
                            System.out.println("[⚠️] Error in translation from" + prevLang + " to " + lang);
                        }
                    }
                }
            }
        }
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = SCRIPT_URL +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
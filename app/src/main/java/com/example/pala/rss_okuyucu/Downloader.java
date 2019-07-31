package com.example.pala.rss_okuyucu;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Downloader {

    public static String getContent(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            URL remoteUrl = new URL(url);
            URLConnection yc = remoteUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
        }
        catch (Exception e) {
        }
        return sb.toString();
    }
}
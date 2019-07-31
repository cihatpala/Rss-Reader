package com.example.pala.rss_okuyucu;



import java.util.List;

public interface ParseFeedCallback {
    public void finishedLoadingFeeds(List<Rss> feeds);
}
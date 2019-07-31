package com.example.pala.rss_okuyucu;

import java.util.List;
import android.os.AsyncTask;


public class ParseFeedAsyncTask extends AsyncTask<Void, Void, List<Rss>> {
    private RssParser parser;
    private String mFeedUrl;
    private ParseFeedCallback   callback;
    public ParseFeedAsyncTask(ParseFeedCallback callback , Rss.Type rssType , String mFeedUrl) {
        this.mFeedUrl=mFeedUrl;
        this.callback = callback;

        switch (rssType){
            case IPHONE:
                this.parser = new RssParser("item","content","dc:date","pubDate","description","title","link",rssType);
                break;
            case MYNET_SONDAKİKA:
                this.parser = new RssParser("item","img640x360","dc:date","pubDate","description","title","link",rssType);
                break;
            case MYNET_TÜMHABERLER:
                this.parser = new RssParser("item","img640x360","dc:date","pubDate","description","title","link",rssType);
                break;
            case MYNET_TEKNOLOJİ:
                this.parser = new RssParser("item","img640x360","dc:date","pubDate","description","title","link",rssType);
                break;
            case BBC_HABER:
                this.parser = new RssParser("item","img640x360","dc:date","pubDate","description","title","link",rssType);
                break;                                    //(imageIcerik gelmiyor.)
            case CNN_KULTUR_SANAT:
                this.parser = new RssParser("item","img640x360","dc:date","pubDate","description","title","link",rssType);
                break;                                     //(imageIcerik gelmiyor.)
            case GLOBAL_NEWS:
                this.parser = new RssParser("item","img640x360","dc:date","pubDate","description","title","link",rssType);
                break;                                    //(imageIcerik gelmiyor.)

        }
    }
    @Override
    protected List<Rss> doInBackground(Void... params) {
        String xmlContent = Downloader.getContent(mFeedUrl);
        return parser.parseFeed(xmlContent);
    }
    @Override
    protected void onPostExecute(List<Rss> result) {
        super.onPostExecute(result);
        callback.finishedLoadingFeeds(result);
    }
}
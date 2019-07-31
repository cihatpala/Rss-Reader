package com.example.pala.rss_okuyucu;
import java.lang.reflect.Type;

public class Rss {
    private Type rssType;
    private String ıvır;
    private String title;
    private String content;
    private String postDate;
    private String imageUrl;
    private String originalPostUrl;

    public enum Type{
        IPHONE , MYNET_SONDAKİKA , MYNET_TÜMHABERLER , MYNET_TEKNOLOJİ , BBC_HABER , CNN_KULTUR_SANAT , GLOBAL_NEWS
    }

    public Type getRssType(){ return rssType; } //rssType döndürür

    public void setRssType(Type rssType){ this.rssType = rssType; } //rssType Yükleme



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getOriginalPostUrl() {
        return originalPostUrl;
    }

    public void setOriginalPostUrl(String originalPostUrl) {
        this.originalPostUrl = originalPostUrl;
    }
    public void setIvır(String  ıvır){ this.ıvır=ıvır; }
    public String getIvır(){ return ıvır;  }
}
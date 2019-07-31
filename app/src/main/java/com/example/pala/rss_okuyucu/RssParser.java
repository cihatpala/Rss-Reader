package com.example.pala.rss_okuyucu;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.util.Log;


public class RssParser {
    Rss rss = new Rss();
    protected String eleman = "item";
    protected String imageIcerik = "item";
    protected String dcdate ="dc:date";
    protected String tarih = "pubDate";
    protected String icerik = "content";
    protected String baslik = "title";
    protected String adres = "link";
    protected Rss.Type typer;
    public RssParser(){
        String eleman = "item";
        String imageIcerik = "item";
        String dcdate ="dc:date";
        String tarih = "pubDate";
        String icerik = "content";
        String baslik = "title";
        String adres = "link";
    }
    public RssParser(String eleman,String imageIcerik,String dcdate,String tarih,String icerik ,String baslik,String adres
            ,Rss.Type typer){
        this.eleman=eleman;
        this.imageIcerik=imageIcerik;
        this.dcdate=dcdate;
        this.tarih=tarih;
        this.icerik=icerik;
        this.baslik=baslik;
        this.adres=adres;
        this.typer=typer;
        rss.setRssType(this.typer);

    }

    public List<Rss> parseFeed(String feedContent) {
        Rss rss = new Rss();
        List<Rss> results = new ArrayList<Rss>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(feedContent));

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_DOCUMENT) {

                }
                else if (eventType == XmlPullParser.START_TAG) {
                    String tagName = xpp.getName();
                    if (eleman.equals(tagName)) {
                        rss = new Rss();
                    }
                    else if (imageIcerik.equals(tagName)) {
                        String desc = xpp.nextText();
                        rss.setImageUrl(desc); //setContent vardı..

                        Pattern p = Pattern.compile(".*<img[^>]*src=\"([^\"]*)", Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(desc);
                        String srcUrl = null;
                        while (m.find()) {
                            srcUrl = m.group(1);
                            rss.setImageUrl(srcUrl.toString().trim());
                        }

                    }

                    else if (dcdate.equals(tagName)) {
                        rss.setPostDate(xpp.nextText());
                    }
                    else if (tarih.equals(tagName)) {
                        rss.setPostDate(xpp.nextText());
                    }
                    else if (icerik.equals(tagName)) {
                        rss.setIvır(xpp.nextText());
                    }
                    else if (baslik.equals(tagName)) {
                        rss.setTitle(xpp.nextText());
                    }
                    else if (adres.equals(tagName)) {
                        rss.setOriginalPostUrl(xpp.nextText());
                    }
                }
                else if (eventType == XmlPullParser.CDSECT) {
                }
                else if (eventType == XmlPullParser.END_TAG) {
                    String tagName = xpp.getName();
                    if (eleman.equals(tagName)) {
                        results.add(rss);
                    }
                }
                else if (eventType == XmlPullParser.TEXT) {
                }
                eventType = xpp.nextToken();

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Main", "Error : " + e.getMessage());
        }
        return results;
    }
}
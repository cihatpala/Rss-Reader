package com.example.pala.rss_okuyucu;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements ParseFeedCallback {
    private final String TAG = getClass().getSimpleName();
    private ListView listView;
    private ImageView image;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        image = (ImageView) findViewById(R.id.rssimg);
        image.setImageResource(R.drawable.bul);

        listView = (ListView) findViewById(R.id.rssListview);
        final Spinner acilirMenu = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter spinAdapter = ArrayAdapter.createFromResource(this,R.array.rssler,android.R.layout.
                simple_spinner_dropdown_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acilirMenu.setAdapter(spinAdapter);
        image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
            acilirMenu.performClick();

        }});

        acilirMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String veriler[]=getResources().getStringArray(R.array.rssler);
                String veriUrl[] = getResources().getStringArray(R.array.rss_url);
                Rss.Type rssType = Rss.Type.values()[position];
                new ParseFeedAsyncTask(MainActivity.this,rssType,veriUrl[position]).execute((Void) null);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Rss rss = (Rss) listView.getAdapter().getItem(position);
                Intent browserIntent
                        = new Intent(Intent.ACTION_VIEW, Uri.parse(rss.getOriginalPostUrl().trim()));
                startActivity(browserIntent);
            }
        });


    }

    //ÇIKIŞ İÇİN BEKLEME (3 Saniye)
    boolean cikis;
    @Override
    public void onBackPressed(){
        if (cikis==true){
            Intent ext = new Intent(Intent.ACTION_MAIN);
            ext.addCategory(Intent.CATEGORY_HOME);
            ext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ext);
            finish();
            System.exit(0);
        }
        Toast.makeText(MainActivity.this,"Çıkış için lütfen yeniden geri tuşuna basınız.",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                cikis=false;
            }
        },3000);
        cikis=true;
    }

    @Override
    public void finishedLoadingFeeds(List<Rss> feeds) {
        listView.setAdapter(new RssListAdapter(getApplicationContext(), feeds));
    }

}
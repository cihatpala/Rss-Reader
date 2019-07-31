package com.example.pala.rss_okuyucu;

import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class RssListAdapter extends BaseAdapter {

    private List<Rss>      rssList;
    private LayoutInflater inflater;
    private Context        context;
    private ImageLoader    imageLoader;

    public RssListAdapter(Context context, List<Rss> rssList) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rssList = rssList;
        this.context = context;
        this.imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public int getCount() {
        return rssList.size();
    }

    @Override
    public Object getItem(int position) {
        return rssList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        Rss rss = rssList.get(position);
        View rowView = view;
        TextView txtTitle;
        TextView txtDate;
        TextView txtIcerik;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row_main, null);
            txtTitle = (TextView) rowView.findViewById(R.id.rss_title);
            txtDate = (TextView) rowView.findViewById(R.id.rss_date);
            txtIcerik = (TextView) rowView.findViewById(R.id.rss_aciklama);
        }

        ImageView imageView = (ImageView) rowView.findViewById(R.id.rss_image);

        String imageUrl = rss.getImageUrl();
        imageView.setImageResource(R.drawable.loading); //Image linki bulunmayan haberlerin fotoğrafı.
        //Aynı zamanda listede kaydırma yapıldığında geçici süreliğine fotoğraf kaybolduğunda loading fotoğrafı ekleniyor.
        //     ( ** Uygulanmadığında yükleme anlarında (0-1 saniye) haberlerin fotoğrafları birbirlerinin yerini alıyordu.)
        imageLoader.displayImage(imageUrl, imageView);
        imageView.setImageResource(R.drawable.loading); //Fotoğrafı olmayan Typlerin  fotoğraflarını loading yapar
                                                        // imageLoader'ın yüklediği fotoğrafı silmez.


        txtTitle = (TextView) rowView.findViewById(R.id.rss_title);
        txtDate = (TextView) rowView.findViewById(R.id.rss_date);
        txtIcerik = (TextView) rowView.findViewById(R.id.rss_aciklama);

        txtTitle.setText(rss.getTitle());
        txtDate.setText(rss.getPostDate());
        txtIcerik.setText(rss.getIvır());

        return rowView;
    }

}
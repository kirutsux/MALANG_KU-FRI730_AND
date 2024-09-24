package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
    private final Context context;
    private final String[] headlines;
    private final int[] images;  // Array of resource IDs for images

    public NewsAdapter(Context context, String[] headlines, int[] images) {
        this.context = context;
        this.headlines = headlines;
        this.images = images;
    }

    @Override
    public int getCount() {
        return headlines.length;
    }

    @Override
    public Object getItem(int position) {
        return headlines[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // Inflate the custom layout for each list item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_news, parent, false);
        }

        // Get references to the views
        ImageView imageView = view.findViewById(R.id.news_image);
        TextView headlineTextView = view.findViewById(R.id.news_headline);

        // Set the image and headline for the current item
        imageView.setImageResource(images[position]);
        headlineTextView.setText(headlines[position]);

        return view;
    }
}
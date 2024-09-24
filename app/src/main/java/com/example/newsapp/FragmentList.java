package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.ListFragment;

public class FragmentList extends ListFragment {

    // Interface for communicating with the MainActivity when an item is selected
    OnNewsSelectedListener callback;

    // Sample news images (replace with actual image resources in your project)
    private final int[] newsImages = {
            R.drawable.news1,  // Replace with your actual drawable images
            R.drawable.news2,
            R.drawable.news3,
            R.drawable.news4,
            R.drawable.news5
    };

    public interface OnNewsSelectedListener {
        void onNewsSelected(int position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Use the custom adapter to display the news headlines and images
        NewsAdapter adapter = new NewsAdapter(getActivity(), NewsData.NEWS_HEADLINES, newsImages);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the activity when a list item is clicked
        callback.onNewsSelected(position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnNewsSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNewsSelectedListener");
        }
    }
}
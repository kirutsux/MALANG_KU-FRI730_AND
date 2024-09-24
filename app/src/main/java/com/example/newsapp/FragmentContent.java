package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentContent extends Fragment {

    public static final String ARG_POSITION = "position";
    private int currentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_content layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the arguments passed to the fragment (the selected position)
        Bundle args = getArguments();
        if (args != null) {
            // Update the content view with the selected position
            updateContentView(view, args.getInt(ARG_POSITION));
        } else if (currentPosition != -1) {
            // Restore the state if position was previously selected
            updateContentView(view, currentPosition);
        }
    }

    /**
     * Updates the content view based on the selected position.
     * @param view The root view of the fragment.
     * @param position The position of the selected news item.
     */
    public void updateContentView(View view, int position) {
        // Get the TextView in the fragment's layout and set the content based on the selected position
        TextView content = view.findViewById(R.id.news_content);
        content.setText(NewsData.NEWS_CONTENT[position]);

        // Update the current position
        currentPosition = position;
    }
}
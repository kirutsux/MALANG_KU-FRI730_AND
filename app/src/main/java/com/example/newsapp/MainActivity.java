package com.example.newsapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentList.OnNewsSelectedListener {

    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Automatically loads either portrait or landscape layout

        // Check if we're in landscape mode by checking for fragment_content
        View contentFrame = findViewById(R.id.fragment_content);
        isDualPane = contentFrame != null && contentFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState == null) {
            // Always load the list fragment in the fragment_container
            FragmentList listFragment = new FragmentList();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();

            // If we're in landscape mode, also load the content fragment
            if (isDualPane) {
                FragmentContent contentFragment = new FragmentContent();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, contentFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onNewsSelected(int position) {
        // In both portrait and landscape mode, replace the fragment to show content
        FragmentContent newContentFragment = new FragmentContent();
        Bundle args = new Bundle();
        args.putInt(FragmentContent.ARG_POSITION, position);
        newContentFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isDualPane) {
            // In landscape mode, replace the content fragment in the right pane
            transaction.replace(R.id.fragment_content, newContentFragment);
        } else {
            // In portrait mode, replace the list fragment with the content fragment
            transaction.replace(R.id.fragment_container, newContentFragment);
            transaction.addToBackStack(null);  // Allow user to navigate back to the list
        }

        transaction.commit();
    }
}
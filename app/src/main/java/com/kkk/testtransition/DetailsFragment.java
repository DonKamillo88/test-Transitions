package com.kkk.testtransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by DonKamillo on 11.09.2016.
 */

public class DetailsFragment extends Fragment {
    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    private LinearLayout descriptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        initToolbar(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        descriptions = (LinearLayout) view.findViewById(R.id.descriptions);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(R.drawable.im_1);
    }

    @Override
    public void onResume() {
        super.onResume();
        descriptions.setVisibility(View.VISIBLE);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black));
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("");
    }
}


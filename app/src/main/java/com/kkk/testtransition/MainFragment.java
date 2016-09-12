package com.kkk.testtransition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by DonKamillo on 11.09.2016.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    public static final String SHARED_ELEMENT_ARG = "shared_element";
    public static final String TRANSITION_NAME_ARG = "transition_name";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        initToolbar(view);
        return view;
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle(R.string.main_fragment_title);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView image = (ImageView) view.findViewById(R.id.main_image);
        image.setImageResource(R.drawable.im_1);

        RelativeLayout container = (RelativeLayout) view.findViewById(R.id.main_container);
        container.setOnClickListener(this);

        ViewCompat.setTransitionName(image, TRANSITION_NAME_ARG);
    }

    @Override
    public void onClick(View view) {
        DetailsFragment fragment = DetailsFragment.newInstance();

        // Note that we need the API version check here because the actual transition classes (e.g. Fade)
        // are not in the support library and are only available in API 21+. The methods we are calling on the Fragment
        // ARE available in the support library (though they don't do anything on API < 21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view.findViewById(R.id.main_image), SHARED_ELEMENT_ARG)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

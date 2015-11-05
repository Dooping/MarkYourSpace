package com.example.guilherme.memecreator;

/**
 * Created by Guilherme on 05/11/2015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class BottomPictureFragment extends Fragment {

    private static TextView topMemeText;
    private static TextView botMemeText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_picture_fragment, container, false);
        topMemeText = (TextView)view.findViewById(R.id.topMemeText);
        botMemeText = (TextView)view.findViewById(R.id.botMemeText);
        return view;
    }

    public void setMemetext(String top, String bottom){
        topMemeText.setText(top);
        botMemeText.setText(bottom);
    }
}

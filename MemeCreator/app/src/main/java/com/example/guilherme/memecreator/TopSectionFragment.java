package com.example.guilherme.memecreator;

/**
 * Created by Guilherme on 05/11/2015.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TopSectionFragment extends Fragment {

    private static EditText topTextInput;
    private static EditText botTextInput;

    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void createMeme(String top, String bottom);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCommander = (TopSectionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_section_fragment, container, false);

        topTextInput = (EditText)view.findViewById(R.id.topTextInput);
        botTextInput = (EditText)view.findViewById(R.id.bottomTextInput);

       final Button button= (Button)view.findViewById(R.id.button);

        button.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    buttonClicked(v);
                }
            }
        );
        return view;
    }

    public void buttonClicked(View view){
        activityCommander.createMeme(topTextInput.getText().toString(),botTextInput.getText().toString());
    }
}

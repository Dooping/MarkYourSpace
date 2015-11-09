package com.example.guilherme.listexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] buildings = {"Building1","Building2", "Building3", "Building4", "Building5","Building6"};
        ListAdapter guiListAdapter = new CustomAdapter(this,buildings);
        ListView guiList = (ListView) findViewById(R.id.guiList);
        guiList.setAdapter(guiListAdapter);


        guiList.setOnItemClickListener(
               new AdapterView.OnItemClickListener(){
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       String food = String.valueOf(parent.getItemAtPosition(position));
                       Toast.makeText(MainActivity.this, food,Toast.LENGTH_LONG).show();
                   }
               }

        );

    }


}

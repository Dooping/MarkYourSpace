package nf.co.markyourspace.markyourspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class myBuildingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_buildings);

        String[] buildings = {"Building1","Building2", "Building3", "Building4", "Building5","Building6"};
        ListAdapter buildingEntryAdapter = new BuildingEntryAdapter(this,buildings);
        ListView buildingsList = (ListView)findViewById(R.id.buildingsList);
        buildingsList.setAdapter(buildingEntryAdapter);


        buildingsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(myBuildingsActivity.this, food, Toast.LENGTH_LONG).show();
                    }
                }

        );
    }
}

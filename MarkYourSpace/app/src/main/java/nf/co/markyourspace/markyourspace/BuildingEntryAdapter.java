package nf.co.markyourspace.markyourspace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Guilherme on 09/11/2015.
 */
public class BuildingEntryAdapter  extends ArrayAdapter {

    public BuildingEntryAdapter(Context context, String [] buildings) {
        super(context,R.layout.building_entry ,buildings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.building_entry, parent, false);
        else
            customView = convertView;

        String singleBuildingItem = (String) getItem(position);
        TextView buildingName = (TextView)customView.findViewById(R.id.buildingProperties);
        buildingName.setText(singleBuildingItem);
        return customView;
    }
}

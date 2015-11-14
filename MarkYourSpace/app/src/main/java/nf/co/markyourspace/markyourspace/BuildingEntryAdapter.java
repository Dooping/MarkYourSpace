package nf.co.markyourspace.markyourspace;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Guilherme on 09/11/2015.
 */
public class BuildingEntryAdapter  extends ArrayAdapter {

    List<MyBuilding> buildings;

    public BuildingEntryAdapter(Context context, List<MyBuilding> buildings) {
        super(context,R.layout.building_entry ,buildings);
        this.buildings = buildings;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.building_entry, parent, false);
        else
            customView = convertView;

        ImageButton remove = (ImageButton) customView.findViewById(R.id.deleteButton);
        remove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.remove_building)
                        .setMessage(R.string.really_remove_building)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((MYSApp)(getContext().getApplicationContext())).deleteBuilding((MyBuilding) getItem(position));
                                buildings.remove(position);
                                notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });
        MyBuilding singleBuildingItem = (MyBuilding) getItem(position);
        TextView buildingName = (TextView)customView.findViewById(R.id.buildingProperties);
        buildingName.setText(singleBuildingItem.getName());
        return customView;
    }

    @Override
    public int getCount() {
        return buildings.size();
    }

    @Override
    public Object getItem(int pos) {
        return buildings.get(pos);
    }
}

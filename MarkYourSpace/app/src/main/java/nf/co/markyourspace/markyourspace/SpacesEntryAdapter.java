package nf.co.markyourspace.markyourspace;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by davidgago on 16/11/15.
 */
public class SpacesEntryAdapter extends ArrayAdapter{
    SearchView searchView;
    MyBuilding building;

    public SpacesEntryAdapter(Context context, SearchView searchView, MyBuilding building) {
        super(context, R.layout.room_entry, building.getSpaces());
        this.searchView = searchView;
        this.building = building;
    }

    @Override
    public View getView(final int position,View convertView, final ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.room_entry, parent, false);
        else
            customView = convertView;


        ImageButton remove = (ImageButton) customView.findViewById(R.id.deleteButton);
        remove.setFocusable(false);
        setNotifyOnChange(true);
        remove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.remove_reservation)
                        .setMessage(R.string.really_remove_reservation)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                building.removeSpace((MySpace) getItem(position));
                                remove(getItem(position));
                                //getFilter().filter(searchView.getQuery());
                            }

                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });
        MySpace singleReservationItem = (MySpace) getItem(position);
        TextView reservationLabel = (TextView) customView.findViewById(R.id.reservationProperties);
        TextView buildingLabel = (TextView) customView.findViewById(R.id.reservationBuilding);
        TextView dateLabel = (TextView) customView.findViewById(R.id.reservationDate);
        reservationLabel.setText(singleReservationItem.getName());
        String floor = singleReservationItem.getFloor() + " floor";
        buildingLabel.setText(floor);
        String seats = singleReservationItem.getSeats() + " seats";
        dateLabel.setText(seats);
        return customView;
    }
}

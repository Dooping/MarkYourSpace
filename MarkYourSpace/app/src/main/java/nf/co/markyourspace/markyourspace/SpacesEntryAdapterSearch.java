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
 * Created by Guilherme on 23/11/2015.
 */
public class SpacesEntryAdapterSearch extends ArrayAdapter {

    List<MySpace> spaces;

    public SpacesEntryAdapterSearch(Context context, List<MySpace> spaces) {
        super(context, R.layout.room_entry, spaces);

      this.spaces=spaces;
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

package nf.co.markyourspace.markyourspace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 09/11/2015.
 */
public class ReservationEntryAdapter extends ArrayAdapter {

    public ReservationEntryAdapter(Context context, List<MyReservation> reservations) {
        super(context, R.layout.reservation_entry, reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_entry, parent, false);
        else
            customView = convertView;

        MyReservation singleReservationItem = (MyReservation) getItem(position);
        TextView reservationLabel = (TextView) customView.findViewById(R.id.reservationProperties);
        TextView buildingLabel = (TextView) customView.findViewById(R.id.reservationBuilding);
        TextView dateLabel = (TextView) customView.findViewById(R.id.reservationDate);
        reservationLabel.setText(singleReservationItem.getSpaceName());
        buildingLabel.setText(singleReservationItem.getBuildingName());
        dateLabel.setText("12/11/2015 22:00 - 13/11/2015 13:00");
        return customView;
    }

}

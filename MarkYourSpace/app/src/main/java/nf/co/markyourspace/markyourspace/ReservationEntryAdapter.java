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
public class ReservationEntryAdapter extends ArrayAdapter {

    public ReservationEntryAdapter(Context context, String [] reservations) {
        super(context,R.layout.reservation_entry ,reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_entry, parent, false);
        else
            customView = convertView;

        String singleReservationItem = (String) getItem(position);
        TextView reservationName = (TextView)customView.findViewById(R.id.reservationProperties);
        reservationName.setText(singleReservationItem);
        return customView;
    }
}

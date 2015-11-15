package nf.co.markyourspace.markyourspace;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 09/11/2015.
 */
public class ReservationEntryAdapter extends ArrayAdapter {

    List<MyReservation> reservations;

    public ReservationEntryAdapter(Context context, List<MyReservation> reservations) {
        super(context, R.layout.reservation_entry, reservations);
        this.reservations = reservations;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_entry, parent, false);
        else
            customView = convertView;


        ImageButton remove = (ImageButton) customView.findViewById(R.id.deleteButton);
        remove.setFocusable(false);
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
                                ((MYSApp)(getContext().getApplicationContext())).deleteReservation((MyReservation) getItem(position));
                                reservations.remove(position);
                                notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });
        MyReservation singleReservationItem = (MyReservation) getItem(position);
        TextView reservationLabel = (TextView) customView.findViewById(R.id.reservationProperties);
        TextView buildingLabel = (TextView) customView.findViewById(R.id.reservationBuilding);
        TextView dateLabel = (TextView) customView.findViewById(R.id.reservationDate);
        reservationLabel.setText(singleReservationItem.getSpaceName());
        buildingLabel.setText(singleReservationItem.getBuildingName());
        dateLabel.setText("12/11/2015 22:00 - 13/11/2015 13:00");
        return customView;
    }

    @Override
    public int getCount() {
        return reservations.size();
    }

    @Override
    public Object getItem(int pos) {
        return reservations.get(pos);
    }

}

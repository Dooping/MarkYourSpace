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

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;

/**
 * Created by Guilherme on 09/11/2015.
 */
public class ReservationEntryAdapter extends ArrayAdapter {

    SearchView searchView;

    public ReservationEntryAdapter(Context context, List<MyReservation> reservations, SearchView searchView) {
        super(context, R.layout.reservation_entry, reservations);
        this.searchView = searchView;
    }

    @Override
    public View getView(final int position,View convertView, final ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_entry, parent, false);
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
                                ((MYSApp)(getContext().getApplicationContext())).deleteReservation((MyReservation) getItem(position));
                                remove(getItem(position));
                                getFilter().filter(searchView.getQuery());
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
        dateLabel.setText(getDate(singleReservationItem.getStartDate())+" "+getTime(singleReservationItem.getStartHourInMinutes())+" - "+getDate(singleReservationItem.getEndDate())+" "+getTime(singleReservationItem.getEndHourInMinutes()));
        return customView;
    }

    private String getTime(int totMinutes){
       return (totMinutes/60)+":"+(totMinutes%60);
    }
    private String getDate(Date date){
        return DateFormat.getDateInstance().format(date);
    }

}

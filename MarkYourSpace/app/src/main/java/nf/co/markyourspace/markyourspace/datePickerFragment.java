package nf.co.markyourspace.markyourspace;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class datePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int id=getArguments().getInt("editTextId");
        EditText activityButton = (EditText)getActivity().findViewById(id);
        String date =dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        activityButton.setText(date);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        String sDate = getArguments().getString("editTextDate");
        int year,month,day;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            c.setTime(sdf.parse(sDate));
        } catch (Exception e) {

        }
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

}

package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import static nf.co.markyourspace.markyourspace.R.id.editEndDateNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.editEndHourNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.editStartDateNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.editStartHourNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.end;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link newReservation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link newReservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newReservation extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //dates se a reserva vier de um search
    Date sDate;
    Date eDate;


    EditText startDate;
    EditText endDate;
    EditText startHour;
    EditText endHour;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newReservation.
     */
    // TODO: Rename and change types and number of parameters
    public static newReservation newInstance(String param1, String param2) {
        newReservation fragment = new newReservation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public newReservation() {
    }
    public newReservation(Date sDate,Date eDate) {
        this.sDate=sDate;
        this.eDate=eDate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_reservation, container, false);
        startDate= (EditText)view.findViewById(editStartDateNewReservation);
         endDate= (EditText)view.findViewById(editEndDateNewReservation);
        startHour= (EditText)view.findViewById(editStartHourNewReservation);
        endHour= (EditText)view.findViewById(editEndHourNewReservation);
        startDate.setFocusable(false);
        endDate.setFocusable(false);
        startHour.setFocusable(false);
        endHour.setFocusable(false);
        if(sDate!=null){
            startDate.setText(Integer.toString(sDate.getDay())+"/"+Integer.toString(sDate.getMonth())+"/"+Integer.toString(sDate.getYear() + 1900));
        startHour.setText(Integer.toString(sDate.getHours())+":"+Integer.toString(sDate.getMinutes()));
        }
        if(eDate!=null){
            endDate.setText(Integer.toString(eDate.getDay())+"/"+Integer.toString(eDate.getMonth())+"/"+Integer.toString(eDate.getYear()+1900));
            endHour.setText(Integer.toString(eDate.getHours())+":"+Integer.toString(eDate.getMinutes()));
        }

        startDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditDate(startDate.getId(), startDate.getText().toString());
                    }
                }
        );
        endDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditDate(endDate.getId(), endDate.getText().toString());
                    }
                }
        );
        startHour.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditHour(startHour.getId(), startHour.getText().toString());
                    }
                }
        );
        endHour.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditHour(endHour.getId(), endHour.getText().toString());
                    }
                }
        );

        final Button buttonCancel= (Button)view.findViewById(R.id.buttonCancelReservation);
        final Button buttonAdd=(Button)view.findViewById(R.id.buttonAddNewReservation);

        buttonAdd.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonAddClicked();
                    }
                }
        );
        buttonCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonCancelClicked();
                    }
                }
        );
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void clickedEditDate(int editTextId, String date){
        DialogFragment newFragment = new datePickerFragment();
        Bundle args= new Bundle();
        args.putInt("editTextId",editTextId);
        args.putString("editTextDate", date);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void clickedEditHour(int editTextId, String time){
        DialogFragment newFragment = new timerPickerFragment();
        Bundle args= new Bundle();
        args.putInt("editTextId",editTextId);
        args.putString("editTextTime", time);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void buttonCancelClicked(){
        ((AppMenu)getActivity()).onBackPressed();
    }

    private void buttonAddClicked(){
        boolean create = true;
        Date sDate = null,eDate=null,sTime=null,eTime=null, ssDate = null, eeDate=null;
        SimpleDateFormat formatterDate,formatterTime, formatterDateTime ;
        formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        formatterTime=new SimpleDateFormat("H:m");
        formatterDateTime = new SimpleDateFormat("dd/MM/yyyy H:m");
        try {
            ssDate = formatterDateTime.parse(startDate.getText().toString()+" "+startHour.getText().toString());
            eeDate = formatterDateTime.parse(endDate.getText().toString()+" "+endHour.getText().toString());
            sDate= formatterDate.parse(startDate.getText().toString());
            eDate=formatterDate.parse(endDate.getText().toString());
            sTime=formatterTime.parse(startHour.getText().toString());
            eTime=formatterTime.parse(endHour.getText().toString());
            if(ssDate.after(eeDate)){
                create = false;
                new AlertDialog.Builder(getActivity())
                        .setTitle("Reservation")
                        .setMessage("End date must be after Start date")
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } catch (ParseException e) {
            create = false;
            new AlertDialog.Builder(getActivity())
                .setTitle("Reservation")
                .setMessage("Invalid Date")
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        }

        if(create) {
            List<MySpace> spaces = ((MYSApp) getActivity().getApplication()).searchSpaces(ssDate, eeDate, 0, new ArrayList<String>(), new ArrayList<String>());
            for (MySpace s : spaces)
                if (s.getGuid().equals(getArguments().getString("spaceGuid"))) {
                    MyReservation reservation = new MyReservation(getArguments().getString("spaceName"), getArguments().getString("spaceGuid"), getArguments().getString("buildingName"), ssDate, eeDate, (sTime.getHours() * 60 + sTime.getMinutes()), (eTime.getHours() * 60 + eTime.getMinutes()));
                    ((MYSApp) getActivity().getApplication()).addReservation(reservation);
                    getFragmentManager().popBackStack();
                    ((AppMenu) getActivity()).reservationsFragment();
                    return;
                }
        }

        if(create)
            new AlertDialog.Builder(getActivity())
                .setTitle("Reservation")
                .setMessage("Cannot make this reservation")
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
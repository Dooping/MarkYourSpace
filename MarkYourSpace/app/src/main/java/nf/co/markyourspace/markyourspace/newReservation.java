package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static nf.co.markyourspace.markyourspace.R.id.editBuildingCity;
import static nf.co.markyourspace.markyourspace.R.id.editEndDate;
import static nf.co.markyourspace.markyourspace.R.id.editEndHour;
import static nf.co.markyourspace.markyourspace.R.id.editBuildingName;
import static nf.co.markyourspace.markyourspace.R.id.editBuildingAddress;
import static nf.co.markyourspace.markyourspace.R.id.editStartDate;
import static nf.co.markyourspace.markyourspace.R.id.editStartHour;


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
        // Required empty public constructor
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

        final EditText startDate= (EditText)view.findViewById(editStartDate);
        final EditText endDate= (EditText)view.findViewById(editEndDate);
        final EditText startHour= (EditText)view.findViewById(editStartHour);
        final EditText endHour= (EditText)view.findViewById(editEndHour);
        startDate.setFocusable(false);
        endDate.setFocusable(false);
        startHour.setFocusable(false);
        endHour.setFocusable(false);

        startDate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditStartDate();
                    }
                }
        );
        endDate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditEndDate();
                    }
                }
        );
        startHour.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditStartHour();
                    }
                }
        );
        endHour.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditEndHour();
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

    public void showTimePickerDialog(View v,int editTextID) {
        DialogFragment newFragment = new timerPickerFragment();
        Bundle args= new Bundle();
        args.putInt("editTextId",editTextID);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v,int editTextID) {
        DialogFragment newFragment = new datePickerFragment();
        Bundle args= new Bundle();
        args.putInt("editTextId",editTextID);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    public void clickedEditStartDate(){
        showDatePickerDialog(getView(),R.id.editStartDate);
    }
    public void clickedEditEndDate(){
        showDatePickerDialog(getView(),R.id.editEndDate);
    }
    public void clickedEditStartHour(){
        showTimePickerDialog(getView(),R.id.editStartHour);
    }
    public void clickedEditEndHour(){
        showTimePickerDialog(getView(),R.id.editEndHour);
    }

}

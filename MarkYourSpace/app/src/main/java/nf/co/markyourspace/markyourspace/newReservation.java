package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static nf.co.markyourspace.markyourspace.R.id.editEndDateNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.editEndHourNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.editStartDateNewReservation;
import static nf.co.markyourspace.markyourspace.R.id.editStartHourNewReservation;


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
        final EditText startDate= (EditText)view.findViewById(editStartDateNewReservation);
        final EditText endDate= (EditText)view.findViewById(editEndDateNewReservation);
        final EditText startHour= (EditText)view.findViewById(editStartHourNewReservation);
        final EditText endHour= (EditText)view.findViewById(editEndHourNewReservation);
        startDate.setFocusable(false);
        endDate.setFocusable(false);
        startHour.setFocusable(false);
        endHour.setFocusable(false);

        startDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditDate(startDate.getId());
                    }
                }
        );
        endDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditDate(endDate.getId());
                    }
                }
        );
        startHour.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditHour(startHour.getId());
                    }
                }
        );
        endHour.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickedEditHour(endHour.getId());
                    }
                }
        );

        final Button buttonCancel= (Button)view.findViewById(R.id.buttonCancelCreateSpace);

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

    public void clickedEditDate(int editTextId){
        DialogFragment newFragment = new datePickerFragment();
        Bundle args= new Bundle();
        args.putInt("editTextId",editTextId);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void clickedEditHour(int editTextId){
        DialogFragment newFragment = new timerPickerFragment();
        Bundle args= new Bundle();
        args.putInt("editTextId",editTextId);
        newFragment.setArguments(args);
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void buttonCancelClicked(){
        ((AppMenu)getActivity()).onBackPressed();
    }

}
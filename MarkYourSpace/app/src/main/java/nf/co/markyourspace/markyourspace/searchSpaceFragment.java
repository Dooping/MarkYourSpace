package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static nf.co.markyourspace.markyourspace.R.id.editEndDateSearchSpace;
import static nf.co.markyourspace.markyourspace.R.id.editEndHourSearchSpace;
import static nf.co.markyourspace.markyourspace.R.id.editNofSeatsSearchSpace;
import static nf.co.markyourspace.markyourspace.R.id.editSpaceNumberOfSeats;
import static nf.co.markyourspace.markyourspace.R.id.editStartDateSearchSpace;
import static nf.co.markyourspace.markyourspace.R.id.editStartHourSearchSpace;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link searchSpaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link searchSpaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchSpaceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText startDate;
    EditText endDate;
    EditText startHour;
    EditText endHour;
    EditText nSeats;



    private OnFragmentInteractionListener mListener;

    private LinearLayout activitesLayout;
    private LinearLayout featuresLayout;

    public searchSpaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchSpaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static searchSpaceFragment newInstance(String param1, String param2) {
        searchSpaceFragment fragment = new searchSpaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_search_space, container, false);

        startDate= (EditText)view.findViewById(editStartDateSearchSpace);
        endDate= (EditText)view.findViewById(editEndDateSearchSpace);
        startHour= (EditText)view.findViewById(editStartHourSearchSpace);
        endHour= (EditText)view.findViewById(editEndHourSearchSpace);
        nSeats= (EditText)view.findViewById(editNofSeatsSearchSpace);
        startDate.setFocusable(false);
        endDate.setFocusable(false);
        startHour.setFocusable(false);
        endHour.setFocusable(false);

        startDate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditDate(startDate.getId());
                    }
                }
        );
        endDate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditDate(endDate.getId());
                    }
                }
        );
        startHour.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditHour(startHour.getId());
                    }
                }
        );
        endHour.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        clickedEditHour(endHour.getId());
                    }
                }
        );


        final Button buttonSearch= (Button)view.findViewById(R.id.buttonSearchSpacesResults);
        final Button buttonCancel= (Button)view.findViewById(R.id.button1);

        activitesLayout = (LinearLayout) view.findViewById(R.id.activities);
        featuresLayout = (LinearLayout) view.findViewById(R.id.features);
        addTextEdit(activitesLayout);
        addTextEdit(featuresLayout);

        buttonSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonSearchClicked();
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

    public void addTextEdit(final LinearLayout list) {
        final EditText edit = new EditText(getActivity());
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String s = edit.getText().toString();
                if (hasFocus && s.equals(""))
                    addTextEdit(list);
            }
        });
        int a = list.getChildCount();
        list.addView(edit);
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void buttonSearchClicked(){
        List<String> activities = new ArrayList<>();
        List<String> features = new ArrayList<>();
        for(int i = 0; i < activitesLayout.getChildCount(); i++){
            EditText e = (EditText)activitesLayout.getChildAt(i);
            activities.add(e.getText().toString());
        }
        for(int i = 0; i < featuresLayout.getChildCount(); i++){
            EditText e = (EditText)activitesLayout.getChildAt(i);
            features.add(e.getText().toString());
        }

        //get date e time info
        Date sDate = null,eDate=null,sTime=null,eTime=null;
        SimpleDateFormat formatterDate,formatterTime ;
        formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        formatterTime=new SimpleDateFormat("H:m");
        try {
            sDate= formatterDate.parse(startDate.getText().toString());
            eDate=formatterDate.parse(endDate.getText().toString());
            sTime=formatterTime.parse(startHour.getText().toString());
            eTime=formatterTime.parse(endHour.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
/*
        MyReservation reservation= new MyReservation(getArguments().getString("spaceName"),getArguments().getString("spaceGuid"),getArguments().getString("buildingName"),sDate,eDate,(sTime.getHours()*60+sTime.getMinutes()),(eTime.getHours()*60+eTime.getMinutes()));
        ((MYSApp) getActivity().getApplication()).addReservation(reservation);
        getActivity().getSupportFragmentManager().popBackStack(newReservation.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        ((AppMenu)getActivity()).reservationsFragment();

        */


        searchSpacesResultsFragment(sDate,eDate,Integer.parseInt(nSeats.getText().toString()),activities,features);


    }

    public void buttonCancelClicked(){
        ((AppMenu)getActivity()).onBackPressed();

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

    private void searchSpacesResultsFragment(Date sDate, Date sTime, int i, List<String> activities, List<String> features){
        ((AppMenu)getActivity()).searchSpacesResultsFragment(sDate,sTime,i,activities,features);
    }
}

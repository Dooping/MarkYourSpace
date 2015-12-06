package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link detailSpaceViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link detailSpaceViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailSpaceViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //fields info
    private static TextView textFloor;
    private static TextView textNumberOfSeats;

    //dates se a reserva vier de um search
    private static Date startDate;
    private static Date endDate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private MySpace space;
    MyBuilding building;

    private LinearLayout activitesLayout;
    private LinearLayout featuresLayout;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailSpaceViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailSpaceViewFragment newInstance(String param1, String param2) {
        detailSpaceViewFragment fragment = new detailSpaceViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public detailSpaceViewFragment() {
        // Required empty public constructor
    }
    public detailSpaceViewFragment(Date sDate,Date eDate) {
        startDate=sDate;
        endDate=eDate;
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

        View view = inflater.inflate(R.layout.fragment_detail_space_view, container, false);

        space = ((MYSApp) (getActivity().getApplication())).getBuilding(getArguments().getString("buildingGuid")).getSpace(getArguments().getString("spaceGuid"));
        textFloor=(TextView) view.findViewById(R.id.textFloor);
        textFloor.setText(Integer.toString(space.getFloor()));
        textNumberOfSeats=(TextView) view.findViewById(R.id.textNumberOfSeats);
        textNumberOfSeats.setText(Integer.toString(space.getSeats()));

        building = ((MYSApp) (getActivity().getApplication())).getBuilding(space.getBuildingGuid());
        final View buttonGoToBuilding = view.findViewById(R.id.goToBuilding);
        if(!building.hasCoordinates())
            buttonGoToBuilding.setVisibility(View.INVISIBLE);
        else {
            buttonGoToBuilding.setVisibility(View.VISIBLE);
            buttonGoToBuilding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonGoToLocation();
                }
            });
        }

        activitesLayout = (LinearLayout) view.findViewById(R.id.activities);
        featuresLayout = (LinearLayout) view.findViewById(R.id.features);
        for (String s: space.getActivities())
            if(!s.equals(""))
                addTextEdit(activitesLayout,s);
        for (String s: space.getFeatures())
            if(!s.equals(""))
                addTextEdit(featuresLayout,s);


        Button buttonNewReservation = (Button)view.findViewById(R.id.buttonNewReservation);
        buttonNewReservation.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonNewReservationClicked();
                    }
                }
        );

        return view;
    }

    public void addTextEdit(final LinearLayout list,String value) {
        final EditText edit = new EditText(getActivity());
        edit.setFocusable(false);
        edit.setText(value);
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

    public void buttonNewReservationClicked(){
        if(startDate!=null||endDate!=null){
            ((AppMenu) getActivity()).newReservationFragment(space.getGuid(), ((MYSApp)getActivity().getApplication()).getBuilding(getArguments().getString("buildingGuid")).getName(), space.getName(),startDate,endDate);
            startDate=null;
            endDate=null;
        }else {
            ((AppMenu) getActivity()).newReservationFragment(space.getGuid(), ((MYSApp) getActivity().getApplication()).getBuilding(getArguments().getString("buildingGuid")).getName(), space.getName());
        }
    }

    public void buttonGoToLocation(){
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isEdit", false);
        b.putString("name",building.getName());
        b.putDouble("latitude", building.getCoordinates().getLatitude());
        b.putDouble("longitude", building.getCoordinates().getLongitude());
        intent.putExtras(b);
        startActivity(intent);
    }

}

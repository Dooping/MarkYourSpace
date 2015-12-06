package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link newBuildingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link newBuildingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newBuildingFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //input info
    private static EditText name;
    private static EditText address;
    private static EditText city;
    private static EditText zipcode;
    private static EditText type;

    private double latitude;
    private double longitude;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public newBuildingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newBuildingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newBuildingFragment newInstance(String param1, String param2) {
        newBuildingFragment fragment = new newBuildingFragment();
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
        View view =  inflater.inflate(R.layout.fragment_new_building, container, false);
        name = (EditText) view.findViewById(R.id.editBuildingName);
        address = (EditText) view.findViewById(R.id.editBuildingAddress);
        city = (EditText) view.findViewById(R.id.editBuildingCity);
        zipcode = (EditText) view.findViewById(R.id.editZipCode);
        type = (EditText) view.findViewById(R.id.editType);
        type.setFocusable(false);
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Type")
                        .setItems(R.array.buildingTypes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0)
                                    type.setText("Public");
                                else
                                    type.setText("Private");
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        final Button buttonAdd= (Button)view.findViewById(R.id.buttonAddNewBuilding);
        final Button buttonCancel= (Button)view.findViewById(R.id.buttonCancelCreateSpace);
        final Button buttonLocation = (Button)view.findViewById(R.id.buttonSetLocation);

        buttonAdd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //metodo pa criar novo building
                        buttonAddClicked();
                    }
                }
        );

        buttonCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //metodo pa cancelar novo building voltar ao my buildings
                        buttonCancelClicked();
                    }
                }
        );

        buttonLocation.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //metodo pa criar novo building
                        buttonSetLocation();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void buttonAddClicked(){
        boolean create = true;
        MyLatLng coordinates = null;
        if (!(latitude == 0.0 && longitude == 0.0))
            coordinates = new MyLatLng(latitude, longitude);
        MyBuilding newB = new MyBuilding(name.getText().toString(),address.getText().toString(),city.getText().toString(),type.getText().toString(),zipcode.getText().toString(), coordinates);
        if(newB.getName().equals("")){
            create = false;
            new AlertDialog.Builder(getActivity())
                .setTitle("New Building")
                .setMessage("Building name is mandatory")
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        }
        if(create) {
            ((MYSApp) (getActivity().getApplication())).addBuilding(newB);
            getFragmentManager().popBackStack();
            ((AppMenu) getActivity()).buildingDetailViewFragment(newB.getName(), newB.getGuid());
        }
    }

    public void buttonCancelClicked(){
        getFragmentManager().popBackStack();
    }

    public void buttonSetLocation(){
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isEdit",true);
        intent.putExtras(b);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle b = data.getExtras();
                    address.setText(b.getString("address", ""));
                    city.setText(b.getString("city",""));
                    zipcode.setText(b.getString("zipcode",""));
                    latitude = b.getDouble("latitude");
                    longitude = b.getDouble("longitude");
                }
                break;
            }
        }
    }

}

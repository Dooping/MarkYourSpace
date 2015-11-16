package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        name = (EditText) view.findViewById(R.id.editStartDate);
        address = (EditText) view.findViewById(R.id.editStartHour);
        city = (EditText) view.findViewById(R.id.editEndDate);
        zipcode = (EditText) view.findViewById(R.id.editZipCode);
        type = (EditText) view.findViewById(R.id.editType);


        final Button buttonAdd= (Button)view.findViewById(R.id.buttonAddNewBuilding);
        final Button buttonCancel= (Button)view.findViewById(R.id.buttonCancelNewBuilding);

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
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //metodo pa cancelar novo building voltar ao my buildings
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


        MyBuilding newB = new MyBuilding(name.getText().toString(),address.getText().toString(),city.getText().toString(),type.getText().toString(),zipcode.getText().toString());
        ((MYSApp) (getActivity().getApplication())).addBuilding(newB);
        getActivity().getSupportFragmentManager().popBackStack(newBuildingFragment.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        ((AppMenu)getActivity()).buildingDetailViewFragment(newB.getName(), newB.getGuid());
    }

    public void buttonCancelClicked(){
        getFragmentManager().popBackStack();
    }

}

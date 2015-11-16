package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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

    private static EditText name;
    private static EditText address;
    private static EditText city;
    private static EditText zipcode;
    private static EditText type;

    private OnFragmentInteractionListener mListener;

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

        name = (EditText) view.findViewById(R.id.editName);
        address = (EditText) view.findViewById(R.id.editAddress);
        city = (EditText) view.findViewById(R.id.editCity);
        zipcode = (EditText) view.findViewById(R.id.editZipCode);
        type = (EditText) view.findViewById(R.id.editType);

        final Button buttonAdd= (Button)view.findViewById(R.id.button2);
        final Button buttonCancel= (Button)view.findViewById(R.id.button1);

        buttonAdd.setOnClickListener(
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
        //((MYSApp) (getActivity().getApplication())).searchSpaces();
        //((AppMenu) getActivity()).XXX);

    }

    public void buttonCancelClicked(){
        getFragmentManager().popBackStack();

    }
}

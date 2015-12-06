package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link newSpace.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link newSpace#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newSpace extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static EditText editSpaceName;
    private static EditText editSpaceFloor;
    private static EditText editSpaceNumberOfSeats;

    private LinearLayout activitesLayout;
    private LinearLayout featuresLayout;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newSpace.
     */
    // TODO: Rename and change types and number of parameters
    public static newSpace newInstance(String param1, String param2) {
        newSpace fragment = new newSpace();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public newSpace() {
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
        View view = inflater.inflate(R.layout.fragment_new_space, container, false);
        Button cancel = (Button) view.findViewById(R.id.buttonCancelCreateSpace);
        Button add = (Button) view.findViewById(R.id.buttonCreateSpace);

        editSpaceName= (EditText) view.findViewById(R.id.editSpaceName);
        editSpaceFloor= (EditText) view.findViewById(R.id.editSpaceFloor);
        editSpaceNumberOfSeats= (EditText) view.findViewById(R.id.editSpaceNumberOfSeats);

        activitesLayout = (LinearLayout) view.findViewById(R.id.activities);
        featuresLayout = (LinearLayout) view.findViewById(R.id.features);
        addTextEdit(activitesLayout);
        addTextEdit(featuresLayout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSpace();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void createSpace(){
        List<String> activities = new ArrayList<>();
        List<String> features = new ArrayList<>();
        for(int i = 0; i < activitesLayout.getChildCount(); i++){
            EditText e = (EditText)activitesLayout.getChildAt(i);
            activities.add(e.getText().toString());
        }
        for(int i = 0; i < featuresLayout.getChildCount(); i++){
            EditText e = (EditText)featuresLayout.getChildAt(i);
            features.add(e.getText().toString());
        }

        String bGuid= getArguments().getString("buildingGuid");
        boolean create = true;
        MySpace newS = new MySpace(bGuid,editSpaceName.getText().toString(),Integer.parseInt(editSpaceFloor.getText().toString()),Integer.parseInt(editSpaceNumberOfSeats.getText().toString()), activities, features);
        MyBuilding building = ((MYSApp) (getActivity().getApplication())).getBuilding(bGuid);
        for (MySpace s : building.getSpaces())
            if(s.getName().equals(newS.getName())) {
                create = false;
                new AlertDialog.Builder(getActivity())
                        .setTitle("New Space")
                        .setMessage("This building already has a space with that name")
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        if(create) {
            ((MYSApp) (getActivity().getApplication())).addSpace(getArguments().getString("buildingGuid"), newS);
            getFragmentManager().popBackStack();
        }
        //((AppMenu)getActivity()).buildingDetailViewFragment(newS.getName(), newS.getGuid());

    }

}

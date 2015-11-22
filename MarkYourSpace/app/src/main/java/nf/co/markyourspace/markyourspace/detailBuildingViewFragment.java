package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link detailBuildingViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link detailBuildingViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailBuildingViewFragment extends Fragment implements AbsListView.OnItemClickListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //fields info
    private static TextView textBuildingName;
    private static TextView textBuildingAddress;
    private static TextView textBuildingZipCode;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;
    private ArrayAdapter mAdapter;

    MyBuilding building;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailBuildingViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailBuildingViewFragment newInstance(String param1, String param2) {
        detailBuildingViewFragment fragment = new detailBuildingViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public detailBuildingViewFragment() {
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
        View view= inflater.inflate(R.layout.fragment_detail_building_view, container, false);
        building = ((MYSApp) (getActivity().getApplication())).getBuilding(getArguments().getString("guid"));
        textBuildingName=(TextView) view.findViewById(R.id.textBuildingName);
        textBuildingName.setText(building.getName());
        textBuildingAddress=(TextView) view.findViewById(R.id.textBuildingAddress);
        textBuildingAddress.setText(building.getAddress());
        textBuildingZipCode=(TextView) view.findViewById(R.id.textBuildingZipCode);
        textBuildingZipCode.setText(building.getZipCode());

        ((AppMenu)getActivity()).setActionBarTitle(building.getName());

        mAdapter = new SpacesEntryAdapter(getActivity(), null, building);
        mListView = (AbsListView) view.findViewById(R.id.listView);
        mListView.setEmptyView(view.findViewById(R.id.empty));
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        setEmptyText("No Spaces");

        View buttonAddSpace = view.findViewById(R.id.buttonAddNewSpace);
        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        itemClicked(((MySpace) parent.getItemAtPosition(position)).getName());
                    }
                }

        );
        buttonAddSpace.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        addNewSpace();
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

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            String space = parent.getItemAtPosition(position).toString();
            Toast.makeText(getActivity(), space, Toast.LENGTH_LONG).show();
        }
    }

    private void addNewSpace(){
        ((AppMenu) getActivity()).newSpace(getArguments().getString("guid"));
    }

    private void itemClicked(String spaceName){
        spaceDetailView(spaceName);
    }

    private void spaceDetailView(String spaceName){
        MySpace sp= ((MYSApp) getActivity().getApplication()).getBuilding(getArguments().getString("guid")).getSpaces().get(findSpace(spaceName));
        ((AppMenu) getActivity()).spaceDetailViewFragment(getArguments().getString("guid"),sp.getName(), sp.getGuid());
    }

    private int findSpace(String spaceName){
        List<MySpace> spaces = ((MYSApp) getActivity().getApplication()).getBuilding(getArguments().getString("guid")).getSpaces();
        for(int i=0;i<spaces.size();i++){
            if(spaces.get(i).getName().equals(spaceName)){
                return i;
            }
        }
        return -1;
    }

}

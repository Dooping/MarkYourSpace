package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link searchResultsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link searchResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchResultsFragment extends Fragment implements AbsListView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Context context;

    private static List<MySpace> searchResults;


    ListView searchResultsList;
    Date sDate;
    Date eDate;
    int nSeats;
    List<String> activities;
    List<String> features;

    private ArrayAdapter mAdapter;

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
     * @return A new instance of fragment searchResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static searchResultsFragment newInstance(String param1, String param2) {
        searchResultsFragment fragment = new searchResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public searchResultsFragment() {
        // Required empty public constructor
    }

    public searchResultsFragment(Date startDate,Date endDate, int numberOfSeats, List<String> activitiesList, List<String> featuresList){
        sDate=startDate;
        eDate=endDate;
        nSeats=numberOfSeats;
        activities=activitiesList;
        features=featuresList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        searchResults = ((MYSApp)(getActivity().getApplication())).searchSpaces(sDate,eDate,nSeats,activities,features);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        searchResultsList=(ListView) view.findViewById(R.id.searchResultsList);
        context=getActivity();
        mAdapter = new SpacesEntryAdapterSearch(context,searchResults);
        searchResultsList.setAdapter(mAdapter);
        searchResultsList.setEmptyView(view.findViewById(android.R.id.empty));
        this.setEmptyText("No Spaces");
        searchResultsList.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MySpace mb = (MySpace)parent.getItemAtPosition(position);
        ((AppMenu) getActivity()).spaceDetailViewFragment(mb.getBuildingGuid(),mb.getName(),mb.getGuid());
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

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = searchResultsList.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    public void itemClicked(String spaceName){
       spaceDetailView(spaceName);
    }

    public void spaceDetailView(String spaceName){
        MySpace mb= searchResults.get(findBuildingPosition(spaceName));
        ((AppMenu) getActivity()).spaceDetailViewFragment(mb.getBuildingGuid(),mb.getName(),mb.getGuid());
    }

    private int findBuildingPosition(String spaceName){
        for(int i=0;i<searchResults.size();i++){
            if(searchResults.get(i).getName().equals(spaceName)) {
                return i;
            }
        }
        return -1;
    }
/*
    public void onResume() {
        inputSearch.setQuery("", false);
        inputSearch.setIconified(true);
        searchResults = ((MYSApp)(getActivity().getApplication())).searchSpaces(sDate,eDate,nSeats,activities,features);
        super.onResume();
    }
*/
}

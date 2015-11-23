package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link myBuildingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link myBuildingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myBuildingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Context context;

    private static List<MyBuilding> buildings;
    SearchView inputSearch;

    ListView buildingsList;


    private ArrayAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myBuildingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myBuildingsFragment newInstance(String param1, String param2) {
        myBuildingsFragment fragment = new myBuildingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public myBuildingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        buildings = ((MYSApp)(getActivity().getApplication())).getMyBuildings();

        /*MyBuilding teste = new MyBuilding("asjhgfk"+buildings.size(), "rua dos bixos","city","private","12345");
        ((MYSApp) (getActivity().getApplication())).addBuilding(teste);
        buildings.add(teste);*/

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_buildings, container, false);
        inputSearch = (SearchView) view.findViewById(R.id.searchView);

        context=getActivity();
        mAdapter = new BuildingEntryAdapter(context,buildings,inputSearch);

        buildingsList = (ListView) view.findViewById(R.id.buildingsList);
        buildingsList.setAdapter(mAdapter);
        buildingsList.setEmptyView(view.findViewById(android.R.id.empty));
        this.setEmptyText("No Buildings");
        buildingsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        itemClicked(((MyBuilding) parent.getItemAtPosition(position)).getName());
                    }
                }

        );
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        final View createNewBuilding = view.findViewById(R.id.createNewBuilding);

        createNewBuilding.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
createNewBuilding();
                    }
                }
        );
        return view;
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = buildingsList.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
    public void onResume() {
        inputSearch.setQuery("", false);
        inputSearch.setIconified(true);
        buildings = ((MYSApp)(getActivity().getApplication())).getMyBuildings();
        super.onResume();
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
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.app_menu, menu);
        if(menu!=null) {
            menu.findItem(R.id.action_settings).setVisible(false);
            menu.findItem(R.id.action_search_icon).setVisible(false);
        }
    }

    public void itemClicked(String buildingName){
        buildingDetailView(buildingName);
    }

    public void buildingDetailView(String buildingName){
        MyBuilding mb= buildings.get(findBuildingPosition(buildingName));
        ((AppMenu) getActivity()).buildingDetailViewFragment(mb.getName(), mb.getGuid());
    }

    private int findBuildingPosition(String buildingName){
        for(int i=0;i<buildings.size();i++){
            if(buildings.get(i).getName().equals(buildingName)) {
                return i;
            }
        }
        return -1;
    }

    private void createNewBuilding(){
        ((AppMenu)getActivity()).newBuildingFragment();
    }
}

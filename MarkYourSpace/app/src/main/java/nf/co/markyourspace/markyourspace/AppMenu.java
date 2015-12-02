package nf.co.markyourspace.markyourspace;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AppMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,searchSpaceFragment.OnFragmentInteractionListener
        ,settingsFragment.OnFragmentInteractionListener,historyFragment.OnFragmentInteractionListener
        ,newSpace.OnFragmentInteractionListener, reservationsFragment.OnFragmentInteractionListener
        , newBuildingFragment.OnFragmentInteractionListener, myBuildingsFragment.OnFragmentInteractionListener
        ,findSpaceFragment.OnFragmentInteractionListener,detailBuildingViewFragment.OnFragmentInteractionListener
        ,newReservation.OnFragmentInteractionListener,detailSpaceViewFragment.OnFragmentInteractionListener,searchResultsFragment.OnFragmentInteractionListener
{

    static Context applicationContext;
    private boolean logout = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        applicationContext = getApplicationContext();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView username = (TextView) navigationView.findViewById(R.id.usernameText);
        TextView userEmail= (TextView) navigationView.findViewById(R.id.userEmailText);
        username.setText(((MYSApp) getApplication()).getUsername());
        userEmail.setText(((MYSApp) getApplication()).getUsername());
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.Fragment fragment = new reservationsFragment();
        replaceFragment(fragment);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (f != null) {
                    updateTitleAndDrawer(f);
                }

            }
        });
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount() == 1 && !logout){
                String reservation = "Press again to Logout";
                Toast.makeText(this, reservation, Toast.LENGTH_LONG).show();
                logout = true;
            }
            else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            }
            else{
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.app_menu, menu);
        return false;//true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
       /* else if(id == R.id.action_add_icon){
            android.support.v4.app.Fragment fragment = new newBuildingFragment();
            replaceFragment(fragment);
            //getSupportFragmentManager().beginTransaction()
                    //.replace(R.id.fragment_container, fragment).commit();
            //updateTitleAndDrawer(fragment);
        }*/
        else if(id == R.id.action_search_icon){
            android.support.v4.app.Fragment fragment = new searchSpaceFragment();
            replaceFragment(fragment);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            android.support.v4.app.Fragment fragment = new reservationsFragment();
            replaceFragmentFromMenu(fragment);
        } else if (id == R.id.nav_my_buildings) {
            android.support.v4.app.Fragment fragment = new myBuildingsFragment();
            replaceFragmentFromMenu(fragment);
        } else if (id == R.id.nav_find) {
            android.support.v4.app.Fragment fragment = new findSpaceFragment();
            replaceFragmentFromMenu(fragment);
            
        } else if (id == R.id.nav_history) {

            //android.support.v4.app.Fragment fragment = new historyFragment();
            android.support.v4.app.Fragment fragment = new newReservation();
            replaceFragmentFromMenu(fragment);
        } else if (id == R.id.nav_settings) {
            android.support.v4.app.Fragment fragment = new settingsFragment();
            replaceFragmentFromMenu(fragment);

        } else if (id == R.id.nav_logout) {
            /*Intent intent = new Intent(AppMenu.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
            startActivity(intent);*/
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragmentFromMenu (android.support.v4.app.Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(backStateName);
        ft.commit();
        logout=false;
    }

    private void replaceFragment (android.support.v4.app.Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(newReservation.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
        logout=false;
    }

    private void updateTitleAndDrawer (Fragment fragment){
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(reservationsFragment.class.getName())){
            setTitle ("Mark Your Space");
        }
        else if (fragClassName.equals(myBuildingsFragment.class.getName())){
            setTitle ("My Buildings");
        }
        else if (fragClassName.equals(findSpaceFragment.class.getName())){
            setTitle ("Find a Space");
        }
        else if (fragClassName.equals(newBuildingFragment.class.getName())){
            setTitle ("New Building");
        }
        else if (fragClassName.equals(newReservation.class.getName())){
            setTitle ("New Reservation");
        }
        else if (fragClassName.equals(searchResultsFragment.class.getName())){
            setTitle ("Search Results");
        }
    }

    public void setActionBarTitle(String title){
        setTitle(title);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void newSpace(String buildingGuid) {
        setActionBarTitle("New Space");
        android.support.v4.app.Fragment fragment = new newSpace();
        Bundle args=new Bundle();
        args.putString("buildingGuid",buildingGuid);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("main").commit();

    }

    public void buildingDetailViewFragment(String name, String guid) {
        setActionBarTitle(name);
        android.support.v4.app.Fragment fragment = new detailBuildingViewFragment();
        Bundle args = new Bundle();
        args.putString("guid", guid);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }

    public void spaceDetailViewFragment(String buildingGuid,String name, String spaceGuid) {
        setActionBarTitle(name);
        android.support.v4.app.Fragment fragment = new detailSpaceViewFragment();
        Bundle args = new Bundle();
        args.putString("spaceGuid", spaceGuid);
        args.putString("buildingGuid", buildingGuid);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }
    public void spaceDetailViewFragment(String buildingGuid,String name, String spaceGuid,Date sDate,Date eDate) {
        setActionBarTitle(name);
        android.support.v4.app.Fragment fragment = new detailSpaceViewFragment(sDate,eDate);
        Bundle args = new Bundle();
        args.putString("spaceGuid", spaceGuid);
        args.putString("buildingGuid", buildingGuid);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }

    public void newReservationFragment(String spaceGuid, String buildingName,String spaceName){
        android.support.v4.app.Fragment fragment = new newReservation();
        Bundle args=new Bundle();
        args.putString("spaceGuid",spaceGuid);
        args.putString("buildingName", buildingName);
        args.putString("spaceName", spaceName);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }

    public void newReservationFragment(String spaceGuid, String buildingName,String spaceName,Date sDate,Date eDate){
        android.support.v4.app.Fragment fragment = new newReservation(sDate,eDate);
        Bundle args=new Bundle();
        args.putString("spaceGuid",spaceGuid);
        args.putString("buildingName", buildingName);
        args.putString("spaceName", spaceName);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }

    public void reservationsFragment(){
        android.support.v4.app.Fragment fragment = new reservationsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("main").commit();
    }

    public void searchSpace(){
        android.support.v4.app.Fragment fragment = new searchSpaceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("main").commit();
    }

    public void newBuildingFragment(){
        android.support.v4.app.Fragment fragment = new newBuildingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("main").commit();
    }

    public void searchSpacesResultsFragment(Date startDate,Date endDate, int nSeats, List<String> activities, List<String> features){
        android.support.v4.app.Fragment fragment = new searchResultsFragment(startDate,endDate,nSeats,activities,features);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("main").commit();
    }

}
package nf.co.markyourspace.markyourspace;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AppMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,newSpace.OnFragmentInteractionListener, reservationsFragment.OnFragmentInteractionListener, newBuildingFragment.OnFragmentInteractionListener, myBuildingsFragment.OnFragmentInteractionListener{

    static Context applicationContext;
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
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.Fragment fragment = new reservationsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setActionBarTitle("Mark my Space");
            android.support.v4.app.Fragment fragment = new reservationsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        } else if (id == R.id.nav_my_buildings) {
            setActionBarTitle("My Buildings");
            android.support.v4.app.Fragment fragment = new myBuildingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
        } else if (id == R.id.nav_find) {
setActionBarTitle("Find a Space");
            
        } else if (id == R.id.nav_history) {
            setActionBarTitle("History");

        } else if (id == R.id.nav_settings) {
            setActionBarTitle("Settings");

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(AppMenu.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    public void newSpace() {
        setActionBarTitle("New Space");
        android.support.v4.app.Fragment fragment = new newSpace();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("main").commit();

    }
}

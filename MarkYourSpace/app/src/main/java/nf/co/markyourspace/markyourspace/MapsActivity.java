package nf.co.markyourspace.markyourspace;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.*;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnMapClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker marker;
    private MarkerOptions markerOptions;
    private boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        isEdit = b.getBoolean("isEdit",false);
        if(!isEdit){
            String name = b.getString("name","Building");
            Double lat = b.getDouble("latitude");
            Double lng = b.getDouble("longitude");
            markerOptions = new MarkerOptions().position(new LatLng(lat,lng)).title(name);
        }

        setContentView(R.layout.activity_maps);
        buildGoogleApiClient();
        mGoogleApiClient.connect();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mLastLocation != null) {
            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
        }
        mMap.setMyLocationEnabled(true);
        if(isEdit)
            mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
                                           @Override
                                           public void onMapLongClick(LatLng latLng) {
                                               if (marker != null)
                                                   marker.remove();
                                               marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Building"));
                                           }
                                       }
            );
        else
            marker = mMap.addMarker(markerOptions);

        mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                final CameraUpdate center =
                        CameraUpdateFactory.newLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
                final CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

                mMap.animateCamera(center, 1000, new CancelableCallback() {
                    @Override
                    public void onFinish() {
                        mMap.animateCamera(zoom, 1000, new CancelableCallback() {
                            @Override
                            public void onFinish() {
                                mMap.animateCamera(center, 1000, new CancelableCallback() {
                                    @Override
                                    public void onFinish() {

                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                });

                return true;
            }
        });


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        mMap.animateCamera(center, 1000, new CancelableCallback() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null && mMap!= null) {
            //LatLng mySpot = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(mySpot).title("Current Location"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(mySpot));
            final CameraUpdate center=
                        CameraUpdateFactory.newLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
                final CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

                mMap.animateCamera(center, 1000, new CancelableCallback() {
                    @Override
                    public void onFinish() {
                        mMap.animateCamera(zoom, 1000, new CancelableCallback() {
                            @Override
                            public void onFinish() {
                                mMap.animateCamera(center, 1000, new CancelableCallback() {
                                    @Override
                                    public void onFinish() {

                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }

                            @Override
                            public void onCancel() {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                });
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String postalCode = addresses.get(0).getPostalCode();
                Intent resultIntent = new Intent();
                Bundle b = new Bundle();
                b.putString("address", address);
                b.putString("city", city);
                b.putString("zipcode", postalCode);
                b.putDouble("latitude", marker.getPosition().latitude);
                b.putDouble("longitude",marker.getPosition().longitude);
                resultIntent.putExtras(b);
                setResult(Activity.RESULT_OK, resultIntent);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        }
        else{
            super.onBackPressed();
        }
    }
}

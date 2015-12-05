package nf.co.markyourspace.markyourspace;

import java.io.Serializable;

/**
 * Created by davidgago on 05/12/15.
 */
public class MyLatLng implements Serializable{

    private static final long serialVersionUID = 0L;

    private double latitude;
    private double longitude;

    public MyLatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

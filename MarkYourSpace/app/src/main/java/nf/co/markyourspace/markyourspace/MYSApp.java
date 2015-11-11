package nf.co.markyourspace.markyourspace;

import android.app.Application;

/**
 * Created by davidgago on 11/11/15.
 */
public class MYSApp extends Application {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

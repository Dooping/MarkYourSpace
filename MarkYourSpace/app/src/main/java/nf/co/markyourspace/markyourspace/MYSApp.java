package nf.co.markyourspace.markyourspace;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by davidgago on 11/11/15.
 */
public class MYSApp extends Application {

    private static final String RESERVATION_FILE = "reservationFile.srl";

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public ArrayList<MyReservation> getReservations(){
        ObjectInputStream input;

        ArrayList<MyReservation> reservations = new ArrayList<>();

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+RESERVATION_FILE)));
            reservations = (ArrayList<MyReservation>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i<reservations.size(); i++)
            if(!reservations.get(i).getUser().equals(username))
                reservations.remove(i);
        return reservations;
    }

    public void addReservation(MyReservation reservation){
        ObjectOutput out;
        ArrayList<MyReservation> reservations = getReservations();
        reservations.add(reservation);

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(new File(getFilesDir(),"")+File.separator+RESERVATION_FILE)));
            out.writeObject(reservations);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(MyReservation reservation){
        ObjectOutput out;
        ArrayList<MyReservation> reservations = getReservations();
        reservations.remove(reservation);

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(new File(getFilesDir(),"")+File.separator+RESERVATION_FILE)));
            out.writeObject(reservations);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

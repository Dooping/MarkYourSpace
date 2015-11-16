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
import java.util.Date;
import java.util.List;

/**
 * Created by davidgago on 11/11/15.
 */
public class MYSApp extends Application {

    private static final String RESERVATION_FILE = "reservationFile.srl";
    private static final String BUILDING_FILE = "buildingFile.srl";

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
        reservation.setUser(username);
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

    public ArrayList<MyBuilding> getBuildings(){
        ObjectInputStream input;

        ArrayList<MyBuilding> buildings = new ArrayList<>();

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+BUILDING_FILE)));
            buildings = (ArrayList<MyBuilding>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i<buildings.size(); i++)
            if(!buildings.get(i).getUser().equals(username))
                buildings.remove(i);
        return buildings;
    }

    public void addBuilding(MyBuilding building){
        ObjectOutput out;
        building.setUser(username);
        ArrayList<MyBuilding> buildings = getBuildings();
        buildings.add(building);

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(new File(getFilesDir(),"")+File.separator+BUILDING_FILE)));
            out.writeObject(buildings);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBuilding(MyBuilding building){
        ObjectOutput out;
        ArrayList<MyBuilding> buildings = getBuildings();
        buildings.remove(building);

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(new File(getFilesDir(),"")+File.separator+BUILDING_FILE)));
            out.writeObject(buildings);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyBuilding getBuilding(String guid){
        ArrayList<MyBuilding> buildings = getBuildings();
        for (MyBuilding b : buildings)
            if(b.getGuid().equals(guid))
                return b;
        return null;
    }
     public List<MySpace> searchSpaces(Date startDate, int startHour, Date endDate, int endHour,int numberOfSeats){
         /*List<MySpace> spacesFound = new ArrayList();
         return spacesFound;*/
         return null;
     }
}

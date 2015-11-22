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

        for (int i = reservations.size() - 1; i >= 0; i--)
            if(!reservations.get(i).getUser().equals(username))
                reservations.remove(i);
        return reservations;
    }

    public void addReservation(MyReservation reservation){
        ObjectOutput out;

        int endH=reservation.getEndHourInMinutes();
        int startH=reservation.getStartHourInMinutes();

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

        for (int i = buildings.size() - 1; i >= 0; i--)
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
     public List<MySpace> searchSpaces(Date startDate, Date endDate, int numberOfSeats, List<String> activities, List<String> features){
         List<MySpace> spacesFound = new ArrayList();
         ArrayList<MyBuilding> buildings = getBuildings();
         for (MyBuilding b : buildings)
             spacesFound.addAll(b.getSpaces());

         if(startDate!=null)
             applyStartDateFilter(spacesFound, startDate);
         if(endDate!=null)
             applyEndDateFilter(spacesFound, endDate);
         applySeatsFilter(spacesFound, numberOfSeats);
         applyActivitiesFilter(spacesFound, activities);
         applyFeaturesFilter(spacesFound, features);

         return spacesFound;
     }

    private void applyStartDateFilter(List<MySpace> spaces, Date startDate){
        List<MyReservation> reservations;
        for (int i = spaces.size() - 1; i >= 0; i--){
            reservations = getSpaceReservations(spaces.get(i).getGuid());
            for(MyReservation r : reservations)
                if(r.getStartDate().before(startDate) && r.getEndDate().after(startDate))
                    spaces.remove(i);
        }
    }

    private void applyEndDateFilter(List<MySpace> spaces, Date endDate){
        List<MyReservation> reservations;
        for (int i = spaces.size() - 1; i >= 0; i--){
            reservations = getSpaceReservations(spaces.get(i).getGuid());
            for(MyReservation r : reservations)
                if(r.getStartDate().before(endDate) && r.getEndDate().after(endDate))
                    spaces.remove(i);
        }
    }

    private void applySeatsFilter(List<MySpace> spaces, int seats){
        for (int i = spaces.size() - 1; i >= 0; i--)
            if(spaces.get(i).getSeats()<seats)
                spaces.remove(i);
    }

    private void applyActivitiesFilter(List<MySpace> spaces, List<String> activities){
        for (int i = spaces.size() - 1; i >= 0; i--)
            for(String a: activities)
                if(!spaces.get(i).getActivities().contains(a))
                    spaces.remove(i);
    }

    private void applyFeaturesFilter(List<MySpace> spaces, List<String> features){
        for (int i = spaces.size() - 1; i >= 0; i--)
            for(String a: features)
                if(!spaces.get(i).getFeatures().contains(a))
                    spaces.remove(i);
    }

    public List<MyReservation> getSpaceReservations(String guid){
        List<MyReservation> reservations = getReservations();
        for (int i = reservations.size() - 1; i >= 0; i--)
            if(!reservations.get(i).getSpaceGuid().equals(guid))
                reservations.remove(i);
        return reservations;
    }

    public void addSpace(String buildingGuid,MySpace space){
        ObjectOutput out;
        ArrayList<MyBuilding> buildings = getBuildings();
        MyBuilding building = getBuilding(buildingGuid);
        int index=getBuildings().indexOf(building);
        building.addSpace(space);
        buildings.set(index,building);

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
}

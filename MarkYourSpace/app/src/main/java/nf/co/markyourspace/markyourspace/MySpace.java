package nf.co.markyourspace.markyourspace;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by Guilherme on 12/11/2015.
 */
public class MySpace implements Serializable{

    private static final long serialVersionUID = 0L;

    private String guid;
    private String buildingGuid;
    private String name;
    private int floor;
    private int seats;
    private List<String> activities;
    private List<String> features;

    public MySpace(String buildingGuid,String name,int floor, int seats, List<String> activities, List<String> features) {
        this.guid = UUID.randomUUID().toString();
        this.buildingGuid=buildingGuid;
        this.name = name;
        this.floor = floor;
        this.seats = seats;
        this.activities = activities;
        this.features = features;
    }

    public String getBuildingGuid() {
        return buildingGuid;
    }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public int getSeats() {
        return seats;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void addActivity(String activity){
        activities.add(activity);
    }

    public void removeActivity(String activity){
        activities.remove(activity);
    }

    public List<String> getFeatures() {
        return features;
    }

    public void addFeature(String feature){
        features.add(feature);
    }

    public void removeFeature(String feature){
        activities.remove(feature);
    }

    public String getGuid() {
        return guid;
    }

    @Override
    public String toString(){
        return name + " " + seats + " " + floor;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof MyBuilding)
            if (((MyBuilding) o).getGuid().equals(this.guid))
                return true;
        return false;
    }
}
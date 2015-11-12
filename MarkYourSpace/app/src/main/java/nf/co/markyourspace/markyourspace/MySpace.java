package nf.co.markyourspace.markyourspace;

import java.util.List;

/**
 * Created by Guilherme on 12/11/2015.
 */
public class MySpace {
    private String name;
    private int floor;
    private int seats;
    private List<String> activities;
    private List<String> features;

    public MySpace(String name,int floor, int seats, List<String> activities, List<String> features) {
        this.name = name;
        this.floor = floor;
        this.seats = seats;
        this.activities = activities;
        this.features = features;
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
}
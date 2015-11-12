package nf.co.markyourspace.markyourspace;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guilherme on 12/11/2015.
 */
public class MyBuilding {

    private String name;
    private String address;
    private String city;
    private String type;
    private int zipCode;
    private List<MySpace> spaces;

    public MyBuilding(String name, String address, String city, String type, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.type = type;
        this.zipCode = zipCode;
        this.spaces = new LinkedList<MySpace>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public int getZipCode() {
        return zipCode;
    }

    public List<MySpace> getSpaces() {
        return spaces;
    }

    public void addSpace(MySpace space){
        spaces.add(space);
    }

    public void removeSpace(MySpace space){
        spaces.remove(space);
    }

}

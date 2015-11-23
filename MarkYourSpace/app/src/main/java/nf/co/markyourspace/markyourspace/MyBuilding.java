package nf.co.markyourspace.markyourspace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Guilherme on 12/11/2015.
 */
public class MyBuilding implements Serializable{

    private static final long serialVersionUID = 0L;
    
    private String guid;
    private String name;
    private String address;
    private String city;
    private String type;
    private String user;
    private String zipCode;
    private List<MySpace> spaces;

    public MyBuilding(String name, String address, String city, String type, String zipCode) {
        this.guid = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.city = city;
        this.type = type;
        this.zipCode = zipCode;
        this.spaces = new ArrayList<>();
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

    public String getZipCode() {
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

    public String getUser() {
        return user;
    }

    public String getGuid() {
        return guid;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString(){
        return name + " " + city + " " + address + " " + zipCode;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof MyBuilding)
            if (((MyBuilding) o).getGuid().equals(this.guid))
                return true;
        return false;
    }

    public MySpace getSpace(String guid){
        for (MySpace b : spaces)
            if(b.getGuid().equals(guid))
                return b;
        return null;
    }
}

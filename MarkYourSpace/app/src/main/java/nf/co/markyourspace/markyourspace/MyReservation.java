package nf.co.markyourspace.markyourspace;

import java.util.Date;

/**
 * Created by davidgago on 09/11/15.
 */
public class MyReservation {
    private String spaceName;
    private String buildingName;
    private String user;
    private Date startDate;
    private Date endDate;
    private int startHourInMinutes;
    private int endHourInMinutes;


    public MyReservation(String spaceName, String buildingName, String user, Date startDate, Date endDate, int startHourInMinutes, int endHourInMinutes) {
        this.spaceName = spaceName;
        this.buildingName = buildingName;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHourInMinutes = startHourInMinutes;
        this.endHourInMinutes = endHourInMinutes;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getUser() {
        return user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getStartHourInMinutes() {
        return startHourInMinutes;
    }

    public int getEndHourInMinutes() {
        return endHourInMinutes;
    }

    @Override
    public String toString(){
        return spaceName + " " + buildingName;
    }
}

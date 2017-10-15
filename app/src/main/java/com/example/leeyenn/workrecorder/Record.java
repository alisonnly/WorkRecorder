package com.example.leeyenn.workrecorder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by leeyenn on 29-09-17.
 * Get Setter for work records
 * date, time, companyname, location, door, type of rubbish, no of trips, price
 * Implement serializable to pass object over intent
 */

public class Record implements Serializable {

    //Variables Declaration
    int intRecordID, intNumOfTrip;
    float fTripPrice;
    String dTripDate, dTripTime, sCompanyName, sCompanyLocation, sCompanyDoor, sTypeOfRubbish;


    public Record() {

    }

    //Constructor
    public Record(int recordID, String tripDate, String dTripTime, String companyName, String companyLocation,
                  String companyDoor, String typeOfRubbish, int numOfTrip, float tripPrice){
        this.intRecordID = recordID;
        this.dTripDate = tripDate;
        this.dTripTime = dTripTime;
        this.sCompanyName = companyName;
        this.sCompanyLocation = companyLocation;
        this.sCompanyDoor = companyDoor;
        this.sTypeOfRubbish = typeOfRubbish;
        this.intNumOfTrip = numOfTrip;
        this.fTripPrice = tripPrice;
    }

    //Setter
    public void setIntRecordID(int recordID){
        this.intRecordID = recordID;
    }

    public void setdTripDate(String tripDate){
        this.dTripDate = tripDate;
    }

    public void setdTripTime(String tripTime){
        this.dTripTime = tripTime;
    }

    public void setsCompanyName(String companyName){
        this.sCompanyName = companyName;
    }

    public void setsCompanyLocation(String companyLocation){
        this.sCompanyLocation = companyLocation;
    }

    public void setsCompanyDoor(String companyDoor){
        this.sCompanyDoor = companyDoor;
    }

    public void setsTypeOfRubbish(String typeOfRubbish){
        this.sTypeOfRubbish = typeOfRubbish;
    }

    public void setIntNumOfTrip(int numOfTrip){
        this.intNumOfTrip = numOfTrip;
    }

    public void setfTripPrice(float tripPrice){
        this.fTripPrice = tripPrice;
    }

    //Getter
    public int getIntRecordID(){
        return intRecordID;
    }

    public String getdTripDate(){
        return dTripDate;
    }

    public String getdTripTime() { return dTripTime; }

    public String getsCompanyName() {
        return sCompanyName;
    }

    public String getsCompanyLocation() {
        return sCompanyLocation;
    }

    public String getsCompanyDoor() {
        return sCompanyDoor;
    }

    public String getsTypeOfRubbish() {
        return sTypeOfRubbish;
    }

    public int getIntNumOfTrip() {
        return intNumOfTrip;
    }

    public float getfTripPrice() {
        return fTripPrice;
    }

}

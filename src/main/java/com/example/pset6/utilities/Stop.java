package com.example.pset6.utilities;

/**
 * Created by garrettcoggon on 5/28/15.
 */

public class Stop {
    private String stopName;
    private String stopDescription;
    private int stopId;

    public String getStopName(){
        return stopName;
    }
    public void setStopName(String stopName){
        this.stopName = stopName;
    }
    public String getStopDescription(){
        return stopDescription;
    }
    public void setStopDescription(String stopDescription){
        this.stopDescription = stopDescription;
    }
    public void setStopID(int stopID) {
        this.stopId = stopID;
    }
    public int getStopId() {return stopId;}
}

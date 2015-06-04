package com.example.pset6.utilities;

/**
 * Created by garrettcoggon on 6/3/15.
 */
public class CombinedTime {
    private Integer stopId;
    private String arrivalTime;
    private long milliTime;

    public void setMilliTime(long milliTime){this.milliTime = milliTime;}
    public long getMilliTime(){return milliTime;}
    public String getArrivalTime(){return arrivalTime;}
    public void setArrivalTime(String arrivalTime){this.arrivalTime = arrivalTime;}
    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer id) {
        this.stopId = stopId;
    }

}

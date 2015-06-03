package com.example.pset6.utilities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by garrettcoggon on 5/28/15.
 */
@Entity
@Table(name = "stops")
public class Stop {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stop_id", unique = true, nullable = false)
    private int stopId;
    @Column(name = "stop_name")
    private String stopName;
    @Column(name = "stop_Desc")
    private String stopDescription;


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

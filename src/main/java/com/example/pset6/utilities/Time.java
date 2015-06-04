package com.example.pset6.utilities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.*;

/**
 * Created by garrettcoggon on 5/28/15.
 */
@Entity
@Table(name= "stop_times")
public class Time {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="stop_id", unique = true, nullable = false)
    private int stopId;
    @Column(name = "arrival_time")
    private String arrivalTime;

    public String getArrivalTime(){return arrivalTime;}
    public void setArrivalTime(String arrivalTime){this.arrivalTime = arrivalTime;}
    public int getStopId() {
        return stopId;
    }

    public void setStopId(Integer id) {
        this.stopId = stopId;
    }

}

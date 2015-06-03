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
    private Integer id;
    @Column(name = "arrival_time")
    private String arrivalTime;

    private long milliTime;

    public String getArrivalTime(){return arrivalTime;}
    public void setArrivalTime(String arrivalTime){this.arrivalTime = arrivalTime;}
    public void setMilliTime(long milliTime){this.milliTime = milliTime;}
    public long getMilliTime(){return milliTime;}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

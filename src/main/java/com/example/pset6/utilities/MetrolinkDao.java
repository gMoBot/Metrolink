package com.example.pset6.utilities;

import java.util.List;

/**
 * Created by garrettcoggon on 5/27/15.
 */
public interface MetrolinkDao {

    public List<Stop> getStopsAllStops();
    public List<Stop> validateStop(String stationName);
    public List<Time> nextTrainTime(int stopId);

    }

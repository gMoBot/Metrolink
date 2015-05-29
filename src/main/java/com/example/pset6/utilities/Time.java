package com.example.pset6.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by garrettcoggon on 5/28/15.
 */
public class Time {
    private String arrivalTime;
    private long milliTime;

    public String getArrivalTime(){return arrivalTime;}
    public void setArrivalTime(String arrivalTime){this.arrivalTime = arrivalTime;}
    public void setMilliTime(long milliTime){this.milliTime = milliTime;}
    public long getMilliTime(){return milliTime;}
    public List getTimeUntilArrival(List<Time> times){
        List<Time> milliList = new ArrayList<Time>();
        for (Time time : times){
            Time combinedTime = new Time();
            combinedTime.setArrivalTime(time.getArrivalTime());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            try {
                Date date = sdf.parse(time.arrivalTime);
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(date);
                combinedTime.setMilliTime(date.getTime());
                milliList.add(combinedTime);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        Date date = new Date();
        //TODO: update return value
//        NextArrival(milliList);
        return milliList;
    }
    public int NextArrival(List<Time> milliList){
        Date date = new Date();
        long currentTime = date.getTime();
        Time largest = milliList.get(milliList.size() - 1);
        long largestTime = largest.getMilliTime();
        long bufferTime = currentTime;

        for (Time time : milliList){
            long x = time.getMilliTime();
            if (x > bufferTime){
                final long diff = Math.abs(x - bufferTime);

                if (diff < largestTime){
                    largestTime = diff;
                    bufferTime = x;
                }
            }
            else {
                continue;
            }
        }
        int timeUntilArrival = (int) Math.abs((bufferTime - currentTime) / 60000);
//        if (milliList.contains(currentTime)){
//            Time nextTrain = milliList.get(milliList.indexOf(currentTime));
//            nextTrain.
//
//        }
        return timeUntilArrival;
    }

}

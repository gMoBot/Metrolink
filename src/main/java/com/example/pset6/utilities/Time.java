package com.example.pset6.utilities;

import sun.jvm.hotspot.utilities.IntArray;

import java.lang.reflect.Array;
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
            String[] splitTime = time.getArrivalTime().split(":");
            int[] intArray = new int[splitTime.length];
            for (int i = 0; i < splitTime.length; i++){
                String timeString = splitTime[i];
                intArray[i] = Integer.parseInt(timeString);
            }
            int secTime = ((intArray[0] * 60 * 60) + (intArray[1] * 60) + intArray[2]);
            combinedTime.setMilliTime(secTime);
            milliList.add(combinedTime);
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            try {
//                Date date = sdf.parse(time.arrivalTime);
//                Calendar calendar = GregorianCalendar.getInstance();
//                calendar.setTime(date);
//                combinedTime.setMilliTime(calendar.getTimeInMillis());
//                milliList.add(combinedTime);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
//        Date date = new Date();
        //TODO: update return value
//        NextArrival(milliList);
        return milliList;
    }
    public int NextArrival(List<Time> milliList){
        Calendar c = Calendar.getInstance();
        long current = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long secondsElapsed = (current - c.getTimeInMillis()) / 1000;

//        Date date = new Date();
//        long currentTime = Math.abs(date.getTime());
//
//        int firstcurr = (int) currentTime;
//        System.out.format("largest = %d\n", firstcurr);
        Time largest = milliList.get(milliList.size() - 1);
        long largestTime = largest.getMilliTime();
        long bufferTime = secondsElapsed;

        int firstbuff = (int) bufferTime;
        System.out.format("largest = %d\n", firstbuff);


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
        int convLargest = (int) bufferTime;
//        int convCurrent = (int) currentTime;
        System.out.format("largest = %d", convLargest);
        int timeUntilArrival = (int) Math.abs(((bufferTime - secondsElapsed)) / 60);
//        if (milliList.contains(currentTime)){
//            Time nextTrain = milliList.get(milliList.indexOf(currentTime));
//            nextTrain.
//
//        }
        return timeUntilArrival;
    }

}

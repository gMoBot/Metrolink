package com.example.pset6.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by garrettcoggon on 5/30/15.
 */
public class TimeCalculator extends Time {

    public List<Time> getTimeUntilArrival(List<Time> times){
        List<Time> milliList = new ArrayList<Time>();
        for (Time time : times){
            buildmilliList(milliList, time);
        }
        return milliList;
    }

    private void buildmilliList(List<Time> milliList, Time time) {
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
    }

    public int nextArrival(List<Time> milliList, long current){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long secondsElapsed = (current - c.getTimeInMillis()) / 1000;

        Time largestListMember = milliList.get(milliList.size() - 1);
        long largestTime = largestListMember.getMilliTime();
        long bufferTime = secondsElapsed;

        for (Time time : milliList){
            long x = time.getMilliTime();
            if (x > bufferTime){
                final long diff = Math.abs(x - bufferTime);

                if (diff < largestTime){
                    largestTime = diff;
                    bufferTime = x;
                }
            }
        }

        int timeUntilArrival = (int) Math.abs(((bufferTime - secondsElapsed)) / 60);

        return timeUntilArrival;
    }
}

package com.example.pset6.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by garrettcoggon on 5/30/15.
 */
public class TimeCalculator {

    public List<CombinedTime> getTimeUntilArrival(List<Time> times){
        List<CombinedTime> milliList = new ArrayList<CombinedTime>();
        for (Time time : times){
            buildmilliList(milliList, time);
        }
        return milliList;
    }

    private void buildmilliList(List<CombinedTime> milliList, Time time) {
        CombinedTime combinedTime = new CombinedTime();
        combinedTime.setArrivalTime(time.getArrivalTime());
        String[] splitTime = time.getArrivalTime().split(":");
        int[] intArray = new int[splitTime.length];
        for (int i = 0; i < splitTime.length; i++){
            String timeString = splitTime[i];
            intArray[i] = Integer.parseInt(timeString);
        }
        long secTime = ((intArray[0] * 60 * 60) + (intArray[1] * 60) + intArray[2]);
        combinedTime.setMilliTime(secTime);
        milliList.add(combinedTime);
    }

    public int nextArrival(List<CombinedTime> milliList, long current){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long secondsElapsed = (current - c.getTimeInMillis()) / 1000;

        CombinedTime largestListMember = milliList.get(milliList.size() - 1);
        long largestTime = largestListMember.getMilliTime();
        long bufferTime = secondsElapsed;

        for (CombinedTime combinedTime : milliList){
            long x = combinedTime.getMilliTime();
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

package com.example.pset6.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.*;

/**
 * Created by garrettcoggon on 5/27/15.
 */
@Component
public class Metrolink {

    public Metrolink(){
    }
    public static void main(String[] varArgs){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Metrolink metrolink = (Metrolink) context.getBean("metrolink");
        metrolink.run();
    }

    private void run() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();

        List<Stop> stopsAllStops1 = getAllStops();
        appOutput.print("Which station are you at? ");
        int stopId = getStopId(input);
        appOutput.print("Calculating time until next arrival... ");


        List<Time> arrivalTimes = metrolinkDao.nextTrainTime(stopId);
//        for (Time time : arrivalTimes) {
//            appOutput.print(String.format("--- %s --- %d", time.getArrivalTime(), time.getStopId()));
//        }
        int minutesUntilNextTrain = getMinutesUntilNextTrain(arrivalTimes);
        appOutput.print(String.format("%d minutes until next scheduled train...", minutesUntilNextTrain));

    }

    private int getMinutesUntilNextTrain(List<Time> arrivalTimes) {
        TimeCalculator timeCalculator = new TimeCalculator();
        List<CombinedTime> milliList = timeCalculator.getTimeUntilArrival(arrivalTimes);
//        for (CombinedTime combinedTime : milliList) {
//            int i = (int) combinedTime.getMilliTime();
//            appOutput.print(String.format("%s,  %d", combinedTime.getArrivalTime(), i));
//        }
        Calendar c = Calendar.getInstance();
        long current = c.getTimeInMillis();
        return timeCalculator.nextArrival(milliList, current);
    }

    private int getStopId(Scanner input) {
        String stationName = input.nextLine().toUpperCase();
        List<Stop> returnedStops = metrolinkDao.validateStop(stationName);
        int stopId = returnedStops.get(0).getStopId();
//        for (int i = 0; i < 1; i++) {
//            for (Stop stop : returnedStops) {
//                stopId = stop.getStopId();
//            }
//            }
//        for (Stop stop : returnedStops) {
            appOutput.print(String.format("--- %d ---", stopId));
//        }
        return stopId;
    }

    private List<Stop> getAllStops() {
        List<Stop> stopsAllStops = metrolinkDao.getStopsAllStops();
        for (Stop stop : stopsAllStops) {
            appOutput.print(String.format("--- %s ---", stop.getStopName()));
        }
        return stopsAllStops;
    }

    @Autowired
    private MetrolinkDao metrolinkDao;
    @Autowired
    private AppOutput appOutput;

}

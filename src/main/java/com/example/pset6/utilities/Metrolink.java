package com.example.pset6.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Console;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Created by garrettcoggon on 5/27/15.
 */
public class Metrolink {

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
        int minutesUntilNextTrain = getMinutesUntilNextTrain(arrivalTimes);
        appOutput.print(String.format(" %d minutes until next scheduled train...", minutesUntilNextTrain));

    }

    private int getMinutesUntilNextTrain(List<Time> arrivalTimes) {
        Time timeUntilArrival = new Time();
        TimeCalculator timeCalculator = new TimeCalculator();
        List<Time> milliList = timeCalculator.getTimeUntilArrival(arrivalTimes);
        Calendar c = Calendar.getInstance();
        long current = c.getTimeInMillis();
        return timeCalculator.nextArrival(milliList, current);
    }

    private int getStopId(Scanner input) {
        String stationName = input.nextLine().toUpperCase();
        List<Stop> returnedStops = metrolinkDao.validateStop(stationName);
        int stopId = 0;
        for (Stop stop : returnedStops) {
            stopId = stop.getStopId();
        }
        return stopId;
    }

    private List<Stop> getAllStops() {
        List<Stop> stopsAllStops = metrolinkDao.getStopsAllStops();
        for (Stop stop : stopsAllStops) {
            appOutput.print(String.format("--- %s ---", stop.getStopName()));
        }
        return stopsAllStops;
    }

    private MetrolinkDao metrolinkDao;
    private AppOutput appOutput;

    public void setMetrolinkDao(MetrolinkDao metrolinkDao) {
        this.metrolinkDao = metrolinkDao;
    }
    public void setAppOutput(AppOutput appOutput) {
        this.appOutput = appOutput;
    }
}

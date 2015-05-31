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

        System.out.println("Which station are you at? ");

        int stopId = getStopId(input);

        System.out.format("Stop id is: %d\n", stopId);
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//        Date date = new Date();
//        String currentTime = timeFormat.format(date);
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
        //TODO: remove for statement
        for (Stop stop : returnedStops) {
            appOutput.print(String.format(" %s is a valid stop...", stop.getStopName()));
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

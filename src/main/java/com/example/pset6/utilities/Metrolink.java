package com.example.pset6.utilities;

import com.sun.tools.hat.internal.util.ArraySorter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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

        List<Stop> stopsAllStops = metrolinkDao.getStopsAllStops();
        for (Stop stop : stopsAllStops) {
            appOutput.print(String.format("--- %s ---", stop.getStopName()));
        }

        System.out.println("Which station are you at? ");
        String stationName = input.nextLine().toUpperCase();
        List<Stop> returnedStops = metrolinkDao.validateStop(stationName);
        int stopId = 0;
        //TODO: remove for statement
        for (Stop stop : returnedStops) {
            appOutput.print(String.format(" %s is a valid stop...", stop.getStopName()));
            stopId = stop.getStopId();
        }

//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//        Date date = new Date();
//        String currentTime = timeFormat.format(date);
        List<Time> arrivalTimes = metrolinkDao.nextTrainTime(stopId);
        Time timeUntilArrival = new Time();
        List<Time> milliList = timeUntilArrival.getTimeUntilArrival(arrivalTimes);
        int minutesUntilNextTrain = timeUntilArrival.NextArrival(milliList);
        appOutput.print(String.format(" %d until next scheduled train...", minutesUntilNextTrain));

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

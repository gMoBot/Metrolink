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

//        List<Time> arrivalTimes = getArrivalTimes(stopId);
//        appOutput.print(String.format("%d", );
        List<Time> arrivalTimes = metrolinkDao.nextTrainTime(stopId);
        int minutesUntilNextTrain = getMinutesUntilNextTrain(arrivalTimes);
        appOutput.print(String.format("%d minutes until next scheduled train...", minutesUntilNextTrain));

    }

    private int getMinutesUntilNextTrain(List<Time> arrivalTimes) {
//        Time timeUntilArrival = new Time();
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
//    public List<Time> getArrivalTimes(int stopId){
//        List list = metrolinkDao.nextTrainTime(stopId);
//        List<Time> arrivalTimes;
//        Map<String, Time> timeMap = new HashMap<>();
//        timeMap.put("arrival_time", list.getArrivalTime)
////        for (Time time : arrivalTimes){
////            time.setArrivalTime(time.getArrivalTime());
////        }
//        return arrivalTimes;
//    }


    @Autowired
    private MetrolinkDao metrolinkDao;
    @Autowired
    private AppOutput appOutput;

//    public void setMetrolinkDao(MetrolinkDao metrolinkDao) {
//        this.metrolinkDao = metrolinkDao;
//    }
//    public void setAppOutput(AppOutput appOutput) {
//        this.appOutput = appOutput;
//    }
}

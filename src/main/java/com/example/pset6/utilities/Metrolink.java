package com.example.pset6.utilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Console;
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

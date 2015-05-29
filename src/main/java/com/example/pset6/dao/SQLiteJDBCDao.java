package com.example.pset6.dao;

import com.example.pset6.utilities.*;
import com.example.pset6.utilities.Time;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by garrettcoggon on 5/27/15.
 */
public class SQLiteJDBCDao implements MetrolinkDao {

    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";

    private AppOutput appOutput;

    public List<Stop> getStopsAllStops() {
        appOutput.print("Fetching Metrolink stations...");
        try (Connection connection = getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stops");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<Stop>();
            while (resultSet.next()) {
                Stop stop = new Stop();
                stop.setStopName(resultSet.getString("stop_name"));
                stop.setStopDescription(resultSet.getString("stop_desc"));
                stops.add(stop);
            }
            return stops;
        } catch (SQLException e){
            throw new RuntimeException("Error retrieving stops");
        }
    }

    public List<Stop> validateStop(String stationName) {
        try (Connection connection = getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stops WHERE stop_name=?");
            preparedStatement.setString(1, stationName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> validStops = new ArrayList<Stop>();
            Stop stop = new Stop();
            stop.setStopName(resultSet.getString("stop_name"));
            stop.setStopDescription(resultSet.getString("stop_desc"));
            stop.setStopID(resultSet.getInt("stop_id"));
            validStops.add(stop);
            return validStops;
        } catch (SQLException e){
        throw new RuntimeException("Error validating stop");
        }
    }

    // TODO: change return type
    public List<Time> nextTrainTime(int stopId) {
        try (Connection connection = getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT arrival_time FROM stop_times WHERE stop_id=? ORDER BY arrival_time");
            preparedStatement.setInt(1, stopId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Time> arrivalTimes = new ArrayList<Time>();
            while (resultSet.next()) {
                Time time = new Time();
                time.setArrivalTime(resultSet.getString("arrival_time"));
                arrivalTimes.add(time);
            }
            return arrivalTimes;
        } catch (SQLException e){
            throw new RuntimeException("Error retrieving train arrival times");
        }
    }
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find class for loading the database", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

    public void setAppOutput(AppOutput appOutput) {
        this.appOutput = appOutput;
    }
}

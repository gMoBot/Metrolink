package com.example.pset6.utilities;

import java.util.List;

/**
 * Created by garrettcoggon on 5/27/15.
 */
public class SQLiteJDBCDao implements MetrolinkDao {

    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";

    private AppOutput appOutput;

    public List<Stop> getStopsAllStops() {
        return null;
    }
}

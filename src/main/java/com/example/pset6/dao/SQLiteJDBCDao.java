package com.example.pset6.dao;

import com.example.pset6.utilities.*;
import com.example.pset6.utilities.Time;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.ldap.PagedResultsControl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by garrettcoggon on 5/27/15.
 */
@Repository
public class SQLiteJDBCDao implements MetrolinkDao {

    @Autowired
    private AppOutput appOutput;
    @Autowired
    private SessionFactory sessionFactoryBean;

    public List<Stop> getStopsAllStops() {
        appOutput.print("Fetching Metrolink stations...");

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(Stop.class);
        List list = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List<Stop> validateStop(String stationName) {

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(Stop.class);
        criteria.add(Restrictions.like("stopName", stationName, MatchMode.EXACT));
        List list = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List<Time> nextTrainTime(int thisStopId) {
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria criteria = sessionFactoryBean.getCurrentSession().createCriteria(Time.class);
        criteria.add(Restrictions.eq("stopId", thisStopId));
        criteria.addOrder(Order.asc("arrivalTime"));
        List list = criteria.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }
}

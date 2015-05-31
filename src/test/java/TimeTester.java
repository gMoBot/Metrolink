import com.example.pset6.dao.SQLiteJDBCDao;
import com.example.pset6.utilities.MetrolinkDao;
import com.example.pset6.utilities.Stop;
import com.example.pset6.utilities.Time;
import com.example.pset6.utilities.TimeCalculator;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static com.example.pset6.dao.SQLiteJDBCDao.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by garrettcoggon on 5/29/15.
 */
public class TimeTester {

    @Test
    public void time1433045267349LShouldReturn7Minutes(){
        // for station: broadway @ park ave id = 13597
        // setup
        SQLiteJDBCDao sqLiteJDBCDao = new SQLiteJDBCDao();
        Time timeUntilArrival = new Time();
        TimeCalculator timeCalculator = new TimeCalculator();
        int stopId = 13597;
        long current = 1433045267349L;

        // action
        List<Time> arrivalTimes = sqLiteJDBCDao.nextTrainTime(stopId);
        List<Time> milliList = timeCalculator.getTimeUntilArrival(arrivalTimes);
        int minutesUntilNextTrain = timeCalculator.nextArrival(milliList, current);

        // assert
        assertEquals(7, minutesUntilNextTrain);
    }

    @Test
    public void time1433047978184LShouldReturn22Minutes(){
        // for station: broadway @ park ave id = 13597
        // setup
        SQLiteJDBCDao sqLiteJDBCDao = new SQLiteJDBCDao();
        Time timeUntilArrival = new Time();
        TimeCalculator timeCalculator = new TimeCalculator();
        int stopId = 13597;
        long current = 1433047978184L;

        // action
        List<Time> arrivalTimes = sqLiteJDBCDao.nextTrainTime(stopId);
        List<Time> milliList = timeCalculator.getTimeUntilArrival(arrivalTimes);
        int minutesUntilNextTrain = timeCalculator.nextArrival(milliList, current);

        // assert
        assertEquals(22, minutesUntilNextTrain);
    }

    @Test
    public void time1433047224595LShouldReturn5Minutes(){
        // for station: kingshighway @ page blvd id = 2358
        // setup
        SQLiteJDBCDao sqLiteJDBCDao = new SQLiteJDBCDao();
        Time timeUntilArrival = new Time();
        TimeCalculator timeCalculator = new TimeCalculator();
        int stopId = 2358;
        long current = 1433047224595L;

        // action
        List<Time> arrivalTimes = sqLiteJDBCDao.nextTrainTime(stopId);
        List<Time> milliList = timeCalculator.getTimeUntilArrival(arrivalTimes);
        int minutesUntilNextTrain = timeCalculator.nextArrival(milliList, current);

        // assert
        assertEquals(5, minutesUntilNextTrain);
    }
}

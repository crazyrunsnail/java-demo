package person.davino;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Calendar;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        Calendar day = Calendar.getInstance();
        day.set(2018, Calendar.FEBRUARY, 1);

        Calendar day2 = Calendar.getInstance();
        day2.setTime(new Date());

        int dayBetween = 0 ;
        while (day2.after(day)){
            dayBetween ++;
            day2.add(Calendar.DATE, -1);
        }
        System.out.println(dayBetween);

    }

}

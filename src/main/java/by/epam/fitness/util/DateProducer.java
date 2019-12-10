package by.epam.fitness.util;

import java.util.Calendar;
import java.util.Date;

/**
 * The type Date producer.
 */
public class DateProducer {

    /**
     * Gets correct date.
     *
     * @param dateToAdd    the date to add
     * @param offsetAmount the offset amount
     * @return the correct date
     */
    public static Date getCorrectDate(Date dateToAdd, Integer offsetAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToAdd);
        calendar.add(Calendar.DAY_OF_YEAR, offsetAmount);
        dateToAdd = calendar.getTime();
        return new java.sql.Date(dateToAdd.getTime());
    }
}

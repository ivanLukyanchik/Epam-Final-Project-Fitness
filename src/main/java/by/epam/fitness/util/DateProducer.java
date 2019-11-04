package by.epam.fitness.util;

import java.util.Calendar;
import java.util.Date;

public class DateProducer {

    public static Date getCorrectDate(Date dateToAdd, Integer offsetAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToAdd);
        calendar.add(Calendar.DAY_OF_YEAR, offsetAmount);
        dateToAdd = calendar.getTime();
        return new java.sql.Date(dateToAdd.getTime());
    }
}

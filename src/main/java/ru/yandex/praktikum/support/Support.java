package ru.yandex.praktikum.support;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Support {
    public static String getNextDayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrowDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, d-е MMMM yyyy г.");
        return format.format(tomorrowDate);
    }
}

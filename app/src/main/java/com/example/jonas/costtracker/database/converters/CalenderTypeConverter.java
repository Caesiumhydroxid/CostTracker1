package com.example.jonas.costtracker.database.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class CalenderTypeConverter {
    @TypeConverter
    public static Calendar toCalendar(Long value){
        if ( value == null)
            return null;
        else
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(value));
            return cal;
        }

    }

    @TypeConverter
    public static Long toLong(Calendar value){
        return value == null ? null : value.getTime().getTime();
    }
}

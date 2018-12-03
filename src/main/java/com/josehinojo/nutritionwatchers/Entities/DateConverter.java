package com.josehinojo.nutritionwatchers.Entities;

import java.util.Date;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }



    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}

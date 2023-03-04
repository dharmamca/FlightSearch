package com.flight.schedule.demo.util;

import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class FlightUtil {

    public static OffsetTime toOffSetTime(String timeStr){
        final String pattern = "HH:MM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        ZoneId defaultZone = ZoneId.systemDefault();
        LocalTime dateTime = LocalTime.parse(timeStr, formatter);
        ZoneOffset offset = defaultZone.getRules().getOffset(LocalDateTime.now());
        OffsetTime offsetDateTime = OffsetTime.of(dateTime, offset);
        return offsetDateTime;

//        DateTimeFormatter dtfB = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("Asia/Kolkata"));;
//        ZonedDateTime datetime = ZonedDateTime.parse(timeStr, dtfB);
//        return datetime.toOffsetDateTime().toOffsetTime();
    }

    public static OffsetDateTime toOffsetDateTime(String dateStr){
        final String pattern = "DD-MMM-YYYY";

        DateTimeFormatter parser = new DateTimeFormatterBuilder().appendPattern(pattern)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                .toFormatter();

        LocalDateTime ldt = LocalDateTime. parse(dateStr, parser);
        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(ldt);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(ldt, offset);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//
//        ZoneId defaultZone = ZoneId.systemDefault();
//        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
//        ZoneOffset offset = defaultZone.getRules().getOffset(dateTime);
//        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateTime, offset);
        return offsetDateTime;


//        DateTimeFormatter dtfB = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("Asia/Kolkata"));;
//        ZonedDateTime datetime = ZonedDateTime.parse(dateStr, dtfB);
//        return datetime.toOffsetDateTime();
    }
}

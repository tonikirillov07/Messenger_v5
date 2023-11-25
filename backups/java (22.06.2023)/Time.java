package com.darkstudio.messenger_v5;

import java.time.*;

public class Time {
    private final Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
    private final String time = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
    private final String date = String.valueOf(LocalDate.now());

    long currentTime = System.currentTimeMillis();

    public long getCurrentTime() {
        return currentTime;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
    
    public LocalTime getLocalTime(){
        return LocalTime.now();
    }
}

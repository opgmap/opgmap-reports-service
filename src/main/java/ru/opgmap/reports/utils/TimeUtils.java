package ru.opgmap.reports.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@NoArgsConstructor
public class TimeUtils {

    public static LocalDateTime now() {
        return LocalDateTime.now(utcZone());
    }

    public static ZoneId utcZone() {
        return ZoneId.of("UTC");
    }
}

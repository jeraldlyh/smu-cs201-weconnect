package com.dsa.graphs.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Time {
    public static long calculateTimeTaken(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MILLIS.between(start, end);
    }
}

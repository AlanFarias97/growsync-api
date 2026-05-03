package com.growsync.dailyreport;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyReportResponse(

        Long id,

        LocalDate reportDate,

        String notes,

        BigDecimal tempTent,

        BigDecimal tempWater,

        BigDecimal humidity,

        BigDecimal ec,

        BigDecimal ppm,

        BigDecimal ph,

        String photoUrl

) {
}
package com.growsync.dailyreport;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateDailyReportRequest(

        @NotNull
        Long plantId,

        @NotNull
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
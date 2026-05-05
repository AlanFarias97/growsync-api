package com.growsync.plant;

import java.time.LocalDate;

public record PlantResponse(

        Long id,
        Long growId,
        String name,
        String strain,
        PlantStage stage,
        LocalDate germinationDate,
        String notes

) {
}
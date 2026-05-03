package com.growsync.plant;

import java.time.LocalDate;

public record PlantResponse(

        Long id,
        String name,
        String strain,
        PlantStage stage,
        LocalDate germinationDate,
        String notes

) {
}
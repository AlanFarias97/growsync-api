package com.growsync.plant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePlantRequest(

        @NotNull
        Long growId,

        @NotBlank
        String name,

        String strain,

        @NotNull
        PlantStage stage,

        LocalDate germinationDate,

        String notes

) {
}
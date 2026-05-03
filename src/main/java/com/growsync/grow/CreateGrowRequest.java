package com.growsync.grow;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGrowRequest(

        @NotBlank
        String name,

        @NotNull
        Medium medium,

        String description

) {
}
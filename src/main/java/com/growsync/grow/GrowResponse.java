package com.growsync.grow;

public record GrowResponse(

        Long id,
        String name,
        Medium medium,
        String description

) {
}
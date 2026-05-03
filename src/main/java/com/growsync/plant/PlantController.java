package com.growsync.plant;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public PlantResponse create(
            @Valid @RequestBody CreatePlantRequest request
    ) {

        return plantService.create(request);
    }

    @GetMapping("/grow/{growId}")
    public List<PlantResponse> findByGrow(
            @PathVariable Long growId
    ) {

        return plantService.findByGrow(growId);
    }
}
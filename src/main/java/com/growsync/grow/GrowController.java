package com.growsync.grow;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grows")
@RequiredArgsConstructor
public class GrowController {

    private final GrowService growService;

    @PostMapping
    public GrowResponse create(
            @Valid @RequestBody CreateGrowRequest request
    ) {

        return growService.create(request);
    }

    @GetMapping
    public List<GrowResponse> findMyGrows() {

        return growService.findMyGrows();
    }
}
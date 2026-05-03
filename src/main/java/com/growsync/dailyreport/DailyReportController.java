package com.growsync.dailyreport;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-reports")
@RequiredArgsConstructor
public class DailyReportController {

    private final DailyReportService dailyReportService;

    @PostMapping
    public DailyReportResponse create(
            @Valid @RequestBody CreateDailyReportRequest request
    ) {

        return dailyReportService.create(request);
    }

    @GetMapping("/plant/{plantId}")
    public List<DailyReportResponse> findByPlant(
            @PathVariable Long plantId
    ) {

        return dailyReportService.findByPlant(plantId);
    }
}
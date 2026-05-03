package com.growsync.dailyreport;

import com.growsync.plant.Plant;
import com.growsync.plant.PlantRepository;
import com.growsync.security.SecurityUtils;
import com.growsync.user.User;
import com.growsync.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyReportService {

    private final DailyReportRepository dailyReportRepository;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;

    public DailyReportResponse create(CreateDailyReportRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Plant plant = plantRepository.findById(request.plantId())
                .orElseThrow();

        // VALIDAR OWNER
        if (!plant.getGrow().getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized plant access");
        }

        // VALIDAR DUPLICADO
        boolean exists = dailyReportRepository
                .findByPlantAndReportDate(
                        plant,
                        request.reportDate()
                )
                .isPresent();

        if (exists) {

            throw new RuntimeException(
                    "A report already exists for this date"
            );
        }

        DailyReport report = DailyReport.builder()
                .plant(plant)
                .reportDate(request.reportDate())
                .notes(request.notes())
                .tempTent(request.tempTent())
                .tempWater(request.tempWater())
                .humidity(request.humidity())
                .ec(request.ec())
                .ppm(request.ppm())
                .ph(request.ph())
                .photoUrl(request.photoUrl())
                .build();

        dailyReportRepository.save(report);

        return mapToResponse(report);
    }

    public List<DailyReportResponse> findByPlant(Long plantId) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow();

        // VALIDAR OWNER
        if (!plant.getGrow().getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized plant access");
        }

        return dailyReportRepository
                .findByPlantOrderByReportDateDesc(plant)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DailyReportResponse mapToResponse(
            DailyReport report
    ) {

        return new DailyReportResponse(
                report.getId(),
                report.getReportDate(),
                report.getNotes(),
                report.getTempTent(),
                report.getTempWater(),
                report.getHumidity(),
                report.getEc(),
                report.getPpm(),
                report.getPh(),
                report.getPhotoUrl()
        );
    }
}
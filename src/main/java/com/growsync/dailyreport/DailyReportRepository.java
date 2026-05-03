package com.growsync.dailyreport;

import com.growsync.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

    List<DailyReport> findByPlantOrderByReportDateDesc(Plant plant);

    Optional<DailyReport> findByPlantAndReportDate(
            Plant plant,
            LocalDate reportDate
    );
}
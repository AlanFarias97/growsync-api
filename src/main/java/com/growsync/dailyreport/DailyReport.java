package com.growsync.dailyreport;

import com.growsync.plant.Plant;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "daily_reports",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_plant_report_date",
                        columnNames = {"plant_id", "report_date"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    private BigDecimal tempTent;

    private BigDecimal tempWater;

    private BigDecimal humidity;

    private BigDecimal ec;

    private BigDecimal ppm;

    private BigDecimal ph;

    @Column(columnDefinition = "TEXT")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {

        updatedAt = LocalDateTime.now();
    }
}
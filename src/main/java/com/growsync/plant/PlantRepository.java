package com.growsync.plant;

import com.growsync.grow.Grow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findByGrow(Grow grow);

}
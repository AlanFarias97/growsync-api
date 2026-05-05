package com.growsync.plant;

import com.growsync.grow.Grow;
import com.growsync.grow.GrowRepository;
import com.growsync.security.SecurityUtils;
import com.growsync.user.User;
import com.growsync.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final GrowRepository growRepository;
    private final UserRepository userRepository;

    public PlantResponse create(CreatePlantRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Grow grow = growRepository.findById(request.growId())
                .orElseThrow();

        // VALIDAR OWNER
        if (!grow.getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized grow access");
        }

        Plant plant = Plant.builder()
                .name(request.name())
                .strain(request.strain())
                .stage(request.stage())
                .germinationDate(request.germinationDate())
                .notes(request.notes())
                .grow(grow)
                .build();

        plantRepository.save(plant);

        return mapToResponse(plant);
    }

    public List<PlantResponse> findByGrow(Long growId) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Grow grow = growRepository.findById(growId)
                .orElseThrow();

        // VALIDAR OWNER
        if (!grow.getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized grow access");
        }

        return plantRepository.findByGrow(grow)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<PlantResponse> findAll() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();


        return plantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PlantResponse mapToResponse(Plant plant) {

        return new PlantResponse(
                plant.getId(),
                plant.getGrow().getId(),
                plant.getName(),
                plant.getStrain(),
                plant.getStage(),
                plant.getGerminationDate(),
                plant.getNotes()
        );
    }
}
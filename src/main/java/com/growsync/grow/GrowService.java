package com.growsync.grow;

import com.growsync.security.SecurityUtils;
import com.growsync.user.User;
import com.growsync.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrowService {

    private final GrowRepository growRepository;
    private final UserRepository userRepository;

    public GrowResponse create(CreateGrowRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Grow grow = Grow.builder()
                .name(request.name())
                .medium(request.medium())
                .description(request.description())
                .user(user)
                .build();

        growRepository.save(grow);

        return mapToResponse(grow);
    }

    public List<GrowResponse> findMyGrows() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return growRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private GrowResponse mapToResponse(Grow grow) {

        return new GrowResponse(
                grow.getId(),
                grow.getName(),
                grow.getMedium(),
                grow.getDescription()
        );
    }
}
package com.growsync.grow;

import com.growsync.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrowRepository extends JpaRepository<Grow, Long> {

    List<Grow> findByUser(User user);

}
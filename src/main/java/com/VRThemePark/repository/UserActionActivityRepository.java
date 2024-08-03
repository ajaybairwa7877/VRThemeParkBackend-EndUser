package com.VRThemePark.repository;

import com.VRThemePark.entity.UserActionActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActionActivityRepository extends JpaRepository<UserActionActivity,Integer> {
    UserActionActivity findByRequestId(String requestId);
}

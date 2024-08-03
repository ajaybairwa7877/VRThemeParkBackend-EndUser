package com.VRThemePark.repository;

import com.VRThemePark.entity.HeaderSection;
import com.VRThemePark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<HeaderSection,Integer> {
}

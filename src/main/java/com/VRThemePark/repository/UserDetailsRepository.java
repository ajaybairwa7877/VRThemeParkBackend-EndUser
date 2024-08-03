package com.VRThemePark.repository;

import com.VRThemePark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}

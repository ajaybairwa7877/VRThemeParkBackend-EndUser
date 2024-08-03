package com.VRThemePark.repository;

import com.VRThemePark.entity.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist,Integer> {

    JwtBlacklist findByTokenEquals(String token);
    @Query(value="select * from jwt_blacklist where token= :token", nativeQuery=true)
    JwtBlacklist findByToken(String token);

}

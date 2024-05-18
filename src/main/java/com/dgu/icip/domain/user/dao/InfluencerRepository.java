package com.dgu.icip.domain.user.dao;

import com.dgu.icip.domain.user.entity.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> ,InfluencerCustom{

//    @Query("select i from Influencer i " +
//            "inner join User u on u.id = i.id " +
//            "where i.id=: id")
//    Optional<Influencer> findInfluencerById(@Param("id")Long id);
}
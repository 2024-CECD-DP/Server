package com.dgu.icip.domain.user.dao;

import com.dgu.icip.domain.user.entity.Influencer;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InfluencerCustom {

    Optional<Influencer> findInfluencerById(Long id);

}

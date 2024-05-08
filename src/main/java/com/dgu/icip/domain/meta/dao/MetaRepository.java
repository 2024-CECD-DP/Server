package com.dgu.icip.domain.meta.dao;

import com.dgu.icip.domain.meta.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MetaRepository extends JpaRepository<Meta, Long> {

    // 특정 인플루언서의 팔로워 수를 가져옵니다.
    @Query("select m.followerCnt from Meta m where m.user =: userId")
    int getFollowerCntByUserId(Long userId);
}

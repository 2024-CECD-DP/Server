package com.dgu.icip.domain.meta.dao;

import com.dgu.icip.domain.user.dto.ReportResponse;
import com.dgu.icip.domain.user.dto.ReportResponse.FollowerTrend;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetaCustom {

    // 특정 인플루언서의 현재 팔로워 수를 가져옵니다.
    int getFollowerCntByUserId(Long userId);

    // 팔로워 추이 데이터를 List형태로 가져옵니다.(무식하게 모든 날짜의 데이터를 다 가져온다.)
    // TODO: 추이 데이터 / 추이데이터 + 평균데이터 무엇이 더 효율적일까? 차이 없으려나
    List<FollowerTrend> getFollowerTrendByUserId(Long userId);

}

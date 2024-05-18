package com.dgu.icip.domain.meta.dao;

import com.dgu.icip.domain.meta.entity.QMeta;
import com.dgu.icip.domain.user.dto.QReportResponse_FollowerTrend;
import com.dgu.icip.domain.user.dto.ReportResponse;
import com.dgu.icip.domain.user.dto.ReportResponse.FollowerTrend;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dgu.icip.domain.meta.entity.QMeta.meta;

@RequiredArgsConstructor
public class MetaRepositoryImpl implements MetaCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int getFollowerCntByUserId(Long userId) {
        return jpaQueryFactory.select(meta.followerCnt)
                .from(meta)
                .orderBy(meta.collected_date.desc())
                .where(meta.user.id.eq(userId))
                .fetchFirst();
    }


    @Override
    public List<FollowerTrend> getFollowerTrendByUserId(Long userId) {
        return jpaQueryFactory.select(new QReportResponse_FollowerTrend(meta.collected_date,meta.followerCnt))
                .from(meta)
                .orderBy(meta.collected_date.asc())
                .where(meta.user.id.eq(userId))
                .fetch();

        /*
        @QeuryProjections을 사용
        단점: QueryDSL 어노테이션 유지로인한 의존성, Q파일 생성 유지
        장점: 컴파일러로 타입 체크 가능(Constructor는 런타임 오류로 체크 가능)
         */
    }


}

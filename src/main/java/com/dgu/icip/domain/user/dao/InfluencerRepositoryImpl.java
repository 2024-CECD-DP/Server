package com.dgu.icip.domain.user.dao;

import com.dgu.icip.domain.user.entity.Influencer;
import com.dgu.icip.domain.user.entity.QInfluencer;
import com.dgu.icip.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.dgu.icip.domain.user.entity.QInfluencer.influencer;
import static com.dgu.icip.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class InfluencerRepositoryImpl implements InfluencerCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<Influencer> findInfluencerById(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(influencer)
//                .join(user)
//                .on(user.id.eq(influencer.id))
                .where(influencer.id.eq(id))
                .fetchOne());
    }
}

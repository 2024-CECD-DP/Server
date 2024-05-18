package com.dgu.icip.domain.media.dao;

import com.dgu.icip.domain.media.entity.Media;
import com.dgu.icip.domain.user.dto.QUploadCycleQueryDto;
import com.dgu.icip.domain.user.dto.UploadCycleQueryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.dgu.icip.domain.image.entity.QImage.image;
import static com.dgu.icip.domain.media.entity.QMedia.media;

@RequiredArgsConstructor
public class MediaRepositoryImpl implements MediaCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Media> getTopThreeMediaById(Long userId) {
        return jpaQueryFactory.selectFrom(media)
                .leftJoin(media.images, image).fetchJoin() // for N+1 문제
                .offset(0)
                .limit(3)
                .orderBy(media.commentCnt.add(media.likeCnt).desc())
                .where(media.user.id.eq(userId))
                .fetch();
    }

    @Override
    public int getMediaCntByUserId(Long userid) {
        return Math.toIntExact(jpaQueryFactory.select(media.count())
                .from(media)
                .where(media.user.id.eq(userid))
                .fetchOne());
    }

    @Override
    public List<UploadCycleQueryDto> getUploadDatesByUserId(Long userId) {
        return jpaQueryFactory.select(new QUploadCycleQueryDto(media.creationDate, media.creationDate.count().intValue()))
                .from(media)
                .where(media.user.id.eq(userId))
                .groupBy(media.creationDate)
                .orderBy(media.creationDate.asc())
                .fetch();
    }
}

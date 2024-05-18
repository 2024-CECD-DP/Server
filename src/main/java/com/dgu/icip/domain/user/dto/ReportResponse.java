package com.dgu.icip.domain.user.dto;

import com.dgu.icip.domain.media.entity.Media;
import com.dgu.icip.domain.user.entity.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ReportResponse {

    private Category category; // 인플루언서 카테고리(ex. Beauty, Health ...)
    private int mediaCnt;
    private int followerCnt;
    private int influenceIndex; //영향력 지수(M)
    private int adIndex; // 광고 지수(M)
    private ProfileResponse profile;
    private List<MediaResponse> media;
    private DetailResponse detail;

    @Builder
    @Getter
    public static class ProfileResponse {
        private String instaName;
        private String userName;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MediaResponse {
        private Long mediaId;
        private int likeCnt;
        private int commentCnt;
        private String imageUrl;

        public MediaResponse(Media media) {
            this.mediaId = media.getId();
            this.likeCnt = media.getLikeCnt();
            this.commentCnt = media.getCommentCnt();
            this.imageUrl = media.getImages().isEmpty() ? null :  media.getImages().get(0).getUrl(); // 해당 게시글의 첫번째 사진의 이미지URL 할당
        }
    }

    @Builder
    @Getter
    public static class DetailResponse {
        private List<FollowerTrend> followerTrends;
        private long likeAvg;
        private long commentAvg;
        private double uploadCycle;
        //activitySummary :
        /*
        TODO: ActivitySummary 부실해보임 => 구현 보류
        활동 요약이라기 보다는 '가장 많이 업로드한 게시글 카테고리 느낌?'
         */
    }

    @Getter
    @Builder
    public static class FollowerTrend {
        private LocalDate date;
        private int followerCnt;

        @QueryProjection
        public FollowerTrend(LocalDate date, int followerCnt) {
            this.date = date;
            this.followerCnt = followerCnt;
        }
    }



}

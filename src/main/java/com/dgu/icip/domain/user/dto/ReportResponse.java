package com.dgu.icip.domain.user.dto;

import com.dgu.icip.domain.media.entity.Media;
import com.dgu.icip.domain.user.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    public static class ProfileResponse {
        private String instaName;
        private String userName;
    }

    @Builder
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
            this.imageUrl = media.getImages().get(0).getUrl(); // 해당 게시글의 첫번째 사진의 이미지URL 할당
        }
    }

    @Builder
    public static class DetailResponse {
        //followTrend
        //followGender
        private int likeAvg;
        private int commentAvg;
        private float uploadCycle;
        //activitySummary

    }


}

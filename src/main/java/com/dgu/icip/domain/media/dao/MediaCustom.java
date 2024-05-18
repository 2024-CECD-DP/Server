package com.dgu.icip.domain.media.dao;

import com.dgu.icip.domain.media.entity.Media;
import com.dgu.icip.domain.user.dto.MediaSearch;
import com.dgu.icip.domain.user.dto.QUploadCycleQueryDto;
import com.dgu.icip.domain.user.dto.UploadCycleQueryDto;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface MediaCustom {

    /**
     * @Purpose 리포팅 시, 대표 미디어 렌더링 시 사용
     * @return 전체 게시글 좋아요+댓글 TOP3 미디어
     */
    List<Media> getTopThreeMediaById(Long userId);

    // 특정 인플루언서의 게시물 수를 가져옵니다.
    int getMediaCntByUserId(Long userid);

    // 특정 인플루언서의 게시글 업로드 날짜 정보를 가져옵니다.
    List<UploadCycleQueryDto> getUploadDatesByUserId(Long userId);
}

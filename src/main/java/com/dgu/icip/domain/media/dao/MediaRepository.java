package com.dgu.icip.domain.media.dao;

import com.dgu.icip.domain.media.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media,Long> {

    // 특정 인플루언서의 게시물 수를 가져옵니다.
    @Query("select count(m) from Media m where m.user =: userId")
    int getMediaCntByUserId(Long userid);

    // 좋아요, 댓글이 가장 많은 게시글 3개를 가져옵니다.(페이징)
    @Query("select m from Media m order by (m.likeCnt + m.commentCnt) desc")
    Page<Media> findTopMediaByLikesAndComments(Pageable pageable);
}

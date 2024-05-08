package com.dgu.icip.domain.user.application;

import com.dgu.icip.domain.media.dao.MediaRepository;
import com.dgu.icip.domain.media.entity.Media;
import com.dgu.icip.domain.meta.dao.MetaRepository;
import com.dgu.icip.domain.user.dao.InfluencerRepository;
import com.dgu.icip.domain.user.dto.ReportResponse;
import com.dgu.icip.domain.user.dto.ReportResponse.MediaResponse;
import com.dgu.icip.domain.user.entity.Influencer;
import com.dgu.icip.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InfluencerService {
    private final InfluencerRepository influencerRepository;
    private final MediaRepository mediaRepository;
    private final MetaRepository metaRepository;

    public ReportResponse report(Long userId, Pageable pageable) {

        /*
        Report Reponse를 만들어 반환하는 과정에서
        ReportResponse DTO를 Builder로 만들기 위해 여러 Repository에서 Query를 날리고,
        이를 DTO로 만들어가는 과정이 매우 복잡하게 느껴짐. 어떻게 바꿀 수 없을까??
        가독성도 안좋고... 하나의 서비스 메서드가 서비스 이외의 것들을 너무 많이 담당하고 있다고 느껴짐.

        책임을 조금 더 분산시키고, DTO도 깔끔하게 정의할 필요가 보인다.. 어떡하지?
         */
        Influencer influencer = influencerRepository.findById(userId)
                .orElseThrow(RuntimeException::new); //TODO: 커스텀 예외 처리로 변경할 것

        int mediaCnt = mediaRepository.getMediaCntByUserId(userId);
        int followerCnt = metaRepository.getFollowerCntByUserId(userId);

        // media
        List<MediaResponse> medias = mediaRepository.findTopMediaByLikesAndComments(pageable).stream() // 대표사진 게시글 3개
                .map(m -> new MediaResponse(m))
                .collect(toList());

        // profile
        ReportResponse.ProfileResponse profile = ReportResponse.ProfileResponse.builder()
                .userName(influencer.getUsername())
                .instaName(influencer.getInstaName())
                .build();

        // detail
        // TODO

        return ReportResponse.builder()
                .category(influencer.getCategory())
                .mediaCnt(mediaCnt)
                .followerCnt(followerCnt)
                .influenceIndex(0)
                .adIndex(0)
                .profile(profile)
                .media(medias) // 대표 게시글 3개
                .detail(null)
                .build();
    }
}

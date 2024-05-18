package com.dgu.icip.domain.user.application;

import com.dgu.icip.domain.media.dao.MediaRepository;
import com.dgu.icip.domain.meta.dao.MetaRepository;
import com.dgu.icip.domain.meta.entity.Meta;
import com.dgu.icip.domain.user.dao.InfluencerRepository;
import com.dgu.icip.domain.user.dto.ReportResponse;
import com.dgu.icip.domain.user.dto.ReportResponse.FollowerTrend;
import com.dgu.icip.domain.user.dto.ReportResponse.MediaResponse;
import com.dgu.icip.domain.user.dto.UploadCycleQueryDto;
import com.dgu.icip.domain.user.entity.Influencer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InfluencerService {
    private final InfluencerRepository influencerRepository;
    private final MediaRepository mediaRepository;
    private final MetaRepository metaRepository;

    /**
     *
     * @param userId
     * @return 인플루언서의 Report page 에서 제공할 데이터 JSON
     */
    public ReportResponse report(Long userId) {

        Influencer influencer = influencerRepository.findInfluencerById(userId)
                .orElseThrow(RuntimeException::new); //TODO: 커스텀 예외 처리로 변경할 것

        // [media]
        int mediaCnt = mediaRepository.getMediaCntByUserId(userId);
        List<MediaResponse> medias = mediaRepository.getTopThreeMediaById(userId).stream() // 대표사진 게시글 3개
                .map(m -> new MediaResponse(m))
                .collect(toList()); //TODO: QueryDSL Projections 통해서가능

        // [profile]
        ReportResponse.ProfileResponse profile = ReportResponse.ProfileResponse.builder()
                .userName(influencer.getUsername())
                .instaName(influencer.getInstaName())
                .build();

        // [detail]

        // 팔로워 추이 데이터(일단 무식하게 모든 날짜의 데이터를 다 가져온다.)
        List<FollowerTrend> followerTrendList = metaRepository.getFollowerTrendByUserId(userId);

        // 마지막 업데이트된 팔로워 수(depend on above query)
        int latestFollowerCnt = getFollowerCntFromFollowerTrendData(followerTrendList);

        // 특정 인플루언서의 최신 Meta(likeAvg, commentAvg)가져오기
        Meta meta = metaRepository.findById(userId).get();

        // 게시글 업로드 주기
        double uploadCycle = calculateUploadCycle(userId);

        //MetaRepository에서 팔로워 추이 데이터 가져오기
        ReportResponse.DetailResponse detail = ReportResponse.DetailResponse.builder()
                .followerTrends(followerTrendList)
                .commentAvg(meta.getCommentAvg())
                .likeAvg(meta.getLikeAvg())
                .uploadCycle(uploadCycle) //TODO: 업로드 주기 계산 로직 추가(OO 마다?)
                .build();

        return ReportResponse.builder()
//                .category(influencer.getCategory()) //TODO: 카테고리는 AI모델 통해 DB에 데이터 가져와야힘.
                .mediaCnt(mediaCnt)
                .followerCnt(latestFollowerCnt)
                .influenceIndex(0)
                .adIndex(0)
                .profile(profile)
                .media(medias) // 대표 게시글 3개
                .detail(detail)
                .build();
    }

    //TODO: 업로드 주기 계산 식 다시 세우기
    private double calculateUploadCycle(Long userId) {
        List<UploadCycleQueryDto> uploadDates = mediaRepository.getUploadDatesByUserId(userId);

        // 총 업로드 수 계산
        int totalUploads = uploadDates.stream().mapToInt(UploadCycleQueryDto::getCount).sum();
        // 전체 기간 계산 (첫 업로드부터 마지막 업로드까지의 일수)
        long totalDays = ChronoUnit.DAYS.between(uploadDates.get(0).getCreationDate(), uploadDates.get(uploadDates.size() - 1).getCreationDate());

        // 평균 업로드 간격 계산 (전체 일수를 총 업로드 수로 나눔)
        double averageInterval = (double)totalUploads / (totalDays/7);

        // 주 단위로 환산
        return  averageInterval;
    }

    private int getFollowerCntFromFollowerTrendData(List<FollowerTrend> followerTrendList) {
        return followerTrendList.get(followerTrendList.size() - 1).getFollowerCnt();
    }
}

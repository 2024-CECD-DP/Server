package com.dgu.icip.domain.user.api;

import com.dgu.icip.domain.user.application.InfluencerService;
import com.dgu.icip.domain.user.dto.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InfluencerController {

    private final InfluencerService influencerService;

    /**
     * @return 인플루언서 분석 요약 리포트 결과
     * @param userId
     */
    @GetMapping("/user/influencer-report/{userId}")
    public ResponseEntity<ReportResponse> report(@PathVariable("userId") Long userId, Pageable pageable) {
        ReportResponse report = influencerService.report(userId, pageable);
        return ResponseEntity.ok(report);
    }


}

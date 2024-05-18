package com.dgu.icip.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UploadCycleQueryDto {

    private LocalDate creationDate;
    private int count;

    @QueryProjection
    public UploadCycleQueryDto(LocalDate creationDate, int count) {
        this.creationDate = creationDate;
        this.count = count;
    }
}

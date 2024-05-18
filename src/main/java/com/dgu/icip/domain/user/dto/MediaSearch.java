package com.dgu.icip.domain.user.dto;

import lombok.Builder;
import lombok.Data;

import static java.lang.Math.max;

@Data
@Builder
public class MediaSearch {
    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 3; // per page

    public long getOffset() {
        return (long) (max(1,page) - 1) * max(size, MAX_SIZE);
    }

}

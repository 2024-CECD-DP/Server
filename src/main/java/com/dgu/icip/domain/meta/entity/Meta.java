package com.dgu.icip.domain.meta.entity;

import com.dgu.icip.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Meta {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "meta_id")
    private Long id;

    private LocalDate collected_date;
    private int followerCnt;
    private long likeAvg;
    private long commentAvg;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user; //TODO: User랑 맺으면 안됐나? Influencer와 맺어야했나? SubType handling에 대해서 미숙하네(일단 진행해보자.)
}

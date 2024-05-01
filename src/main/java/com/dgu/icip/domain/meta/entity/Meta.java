package com.dgu.icip.domain.meta.entity;

import com.dgu.icip.domain.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
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
    private User user;
}

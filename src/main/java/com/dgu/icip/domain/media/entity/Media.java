package com.dgu.icip.domain.media.entity;

import com.dgu.icip.domain.image.entity.Image;
import com.dgu.icip.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Media {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "media_id")
    private Long id;

    private LocalDate collectedDate;
    private LocalDate creationDate;
    private String caption;
    private int likeCnt;
    private int commentCnt;

    @Enumerated(STRING)
    private AdType type;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "media")
    private List<Image> images = new ArrayList<>();
}

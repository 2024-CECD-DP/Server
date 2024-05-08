package com.dgu.icip.domain.image.entity;

import com.dgu.icip.domain.media.entity.Media;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String url;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "media_id")
    private Media media;
}

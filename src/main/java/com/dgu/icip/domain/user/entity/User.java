package com.dgu.icip.domain.user.entity;

import com.dgu.icip.domain.media.entity.Media;
import com.dgu.icip.domain.meta.entity.Meta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = JOINED) // 조인전략 사용
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user")
    private List<Media> medias = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Meta> metas = new ArrayList<>();
}

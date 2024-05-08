package com.dgu.icip.domain.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("Influencer")
public class Influencer extends User{

    private String influencer_id;

    @Enumerated(EnumType.STRING)
    private Category category;

    protected String instaName; // 인스타 계정 이름
}

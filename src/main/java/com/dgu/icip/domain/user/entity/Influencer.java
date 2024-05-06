package com.dgu.icip.domain.user.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Influencer")
public class Influencer extends User{

    private String influencer_id;
    private String instaName; //인스타 계정 이름
}

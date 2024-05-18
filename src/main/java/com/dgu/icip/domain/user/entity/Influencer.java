package com.dgu.icip.domain.user.entity;

import com.dgu.icip.domain.meta.entity.Meta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Influencer")
public class Influencer extends User{

    private String influencer_id;

//    @Enumerated(EnumType.STRING)
//    private Category category;

    private String instaName; // 인스타 계정 이름
}

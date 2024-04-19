package com.dgu.cecd.domain.user.entity;

import com.dgu.cecd.domain.user.type.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user_entity") // PostgreSQL에서 'USER'는 예약어이므로, 테이블명 수정
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Builder
    public User(String email, String password, String name, RoleType role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}

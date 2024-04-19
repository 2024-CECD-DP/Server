package com.dgu.cecd.domain.user.dao;

import com.dgu.cecd.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByEmail(String email);

    Optional<User> findByEmail(String email);
}

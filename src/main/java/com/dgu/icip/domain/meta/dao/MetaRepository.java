package com.dgu.icip.domain.meta.dao;

import com.dgu.icip.domain.meta.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MetaRepository extends JpaRepository<Meta, Long>, MetaCustom {

}

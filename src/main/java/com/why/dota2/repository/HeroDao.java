package com.why.dota2.repository;

import com.why.dota2.domain.HeroDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroDao extends JpaRepository<HeroDO, Integer> {

}

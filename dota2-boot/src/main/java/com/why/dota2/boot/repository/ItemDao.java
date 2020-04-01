package com.why.dota2.boot.repository;

import com.why.dota2.boot.domain.ItemDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<ItemDO, Integer> {

}

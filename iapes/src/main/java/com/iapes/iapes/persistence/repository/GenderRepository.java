package com.iapes.iapes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iapes.iapes.persistence.entity.GenderEntity;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {

}

package com.iapes.iapes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iapes.iapes.persistence.entity.PrivilegeEntity;
@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, Integer> {

}

package com.iapes.iapes.persistence.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iapes.iapes.persistence.entity.SocietieEntity;

@Repository
public interface SocietieRepository extends CrudRepository<SocietieEntity, Integer> {
	List<SocietieEntity>findSocietiesBySocietieEnumIn(List<String> name);
}

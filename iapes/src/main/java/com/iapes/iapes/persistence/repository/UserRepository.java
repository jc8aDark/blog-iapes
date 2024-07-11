package com.iapes.iapes.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iapes.iapes.persistence.entity.UserEntity;
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	Optional<UserEntity> findUserEntityByUsername(String username);
}

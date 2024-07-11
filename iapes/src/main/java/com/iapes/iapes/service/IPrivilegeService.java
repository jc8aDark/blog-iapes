package com.iapes.iapes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iapes.iapes.persistence.entity.PrivilegeEntity;

@Service
public interface IPrivilegeService {
	
	Optional<PrivilegeEntity> buscarPorId(Integer id);
	void guardar(PrivilegeEntity privilegeEntity);
	void eliminar(Integer id);
	List<PrivilegeEntity>buscarTodos();	
}

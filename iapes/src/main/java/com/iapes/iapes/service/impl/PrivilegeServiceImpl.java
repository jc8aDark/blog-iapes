package com.iapes.iapes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iapes.iapes.persistence.entity.PrivilegeEntity;
import com.iapes.iapes.persistence.repository.PrivilegeRepository;
import com.iapes.iapes.service.IPrivilegeService;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {
	
	@Autowired
	private PrivilegeRepository priviRepos;
	
	
	@Override
	public void guardar(PrivilegeEntity privilegeEntity) {
		priviRepos.save(privilegeEntity);
	
	}

	@Override
	public void eliminar(Integer id) {
		priviRepos.deleteById(id);
	}

	@Override
	public Optional<PrivilegeEntity> buscarPorId(Integer id) {
		
		return priviRepos.findById(id);
	}

	@Override
	public List<PrivilegeEntity> buscarTodos() {
		return priviRepos.findAll();
	}



}

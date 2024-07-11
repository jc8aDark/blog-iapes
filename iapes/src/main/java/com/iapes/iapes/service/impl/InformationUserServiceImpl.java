package com.iapes.iapes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iapes.iapes.persistence.entity.InformationUserEntity;
import com.iapes.iapes.persistence.repository.InformationUserRepository;
import com.iapes.iapes.service.IInformationUserService;

@Service
public class InformationUserServiceImpl implements IInformationUserService {
	
	@Autowired
	private InformationUserRepository infoUserRepos;

	@Override
	public List<InformationUserEntity> buscarTodos() {
		return infoUserRepos.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		infoUserRepos.deleteById(id);
	}

	@Override
	public void guardar(InformationUserEntity informationUser) {
		infoUserRepos.save(informationUser);
	}

	@Override
	public Optional<InformationUserEntity> buscarPorId(Integer id) {
		return infoUserRepos.findById(id);
	}

}

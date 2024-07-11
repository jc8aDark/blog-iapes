package com.iapes.iapes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iapes.iapes.persistence.entity.InformationUserEntity;

@Service
public interface IInformationUserService {
	
	List<InformationUserEntity> buscarTodos();
	void eliminar(Integer id);
	void guardar (InformationUserEntity informationUser);
	Optional<InformationUserEntity> buscarPorId(Integer id);

}

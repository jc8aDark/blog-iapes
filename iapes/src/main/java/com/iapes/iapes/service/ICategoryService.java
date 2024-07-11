package com.iapes.iapes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iapes.iapes.persistence.entity.CategoryEntity;

@Service
public interface ICategoryService {
	List<CategoryEntity> buscarTodos();
	void eliminar(Integer id);
	void guardar(CategoryEntity category);
	Optional<CategoryEntity> buscarPorId(Integer id);

}

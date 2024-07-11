package com.iapes.iapes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iapes.iapes.persistence.entity.CategoryEntity;
import com.iapes.iapes.persistence.repository.CategoryRepository;
import com.iapes.iapes.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepos;
	
	@Override
	public List<CategoryEntity> buscarTodos() {
		return categoryRepos.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		categoryRepos.deleteById(id);
	}

	@Override
	public void guardar(CategoryEntity category) {
		categoryRepos.save(category);

	}

	@Override
	public Optional<CategoryEntity> buscarPorId(Integer id) {
		return categoryRepos.findById(id);
	}

}

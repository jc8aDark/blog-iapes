package com.iapes.iapes.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iapes.iapes.controller.dto.CategoryDto;
import com.iapes.iapes.persistence.entity.CategoryEntity;
import com.iapes.iapes.persistence.entity.UserEntity;
import com.iapes.iapes.persistence.repository.UserRepository;
import com.iapes.iapes.service.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoryController {
	

	@Autowired
	private ICategoryService serviceCat;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> buscarTodos() {
        List<CategoryEntity> categoryEntities = serviceCat.buscarTodos();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryDto categoryDto = new CategoryDto();

            categoryDto.setId(categoryEntity.getId());
            categoryDto.setName(categoryEntity.getName());
            categoryDto.setUserId(categoryEntity.getUser() != null ? categoryEntity.getUser().getId() : null);
            categoryDto.setCreated(categoryEntity.getCreated());

            categoryDtos.add(categoryDto);
        }
        return ResponseEntity.ok(categoryDtos);
    }
	
	
		 @PostMapping("/categories")
		 public ResponseEntity<?> guardar(@Valid @RequestBody CategoryDto categoryDTO, BindingResult bindingResult) {
		        if (bindingResult.hasErrors()) {
		            // Manejo de errores de validación
		            List<String> errors = bindingResult.getAllErrors()
		                    .stream()
		                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                    .collect(Collectors.toList());
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		        }

		        CategoryEntity category = new CategoryEntity();
		        category.setName(categoryDTO.getName());
		        category.setCreated(new Date());

		        UserEntity user = userRepository.findById(categoryDTO.getUserId())
		                .orElseThrow(() -> new RuntimeException("User not found"));
		        category.setUser(user);

		        serviceCat.guardar(category);

		        return ResponseEntity.ok(category);
		    }

		 
		 @PutMapping("/categories/{id}")
		 public ResponseEntity<CategoryDto> actualizar(@PathVariable("id") Integer id, @RequestBody CategoryDto categoryDto) {
			try {
				CategoryEntity category = serviceCat.buscarPorId(id)
		                .orElseThrow(() -> new RuntimeException("Category not found"));
			 category.setName(categoryDto.getName());
			 serviceCat.guardar(category);

	            // Construir_y_devolver_la_respuesta_con_la_categoría_actualizada
	            CategoryDto updatedCategoryDto = CategoryDto.builder()
	                    .id(category.getId())
	                    .name(category.getName())
	                    .userId(category.getUser() != null ? category.getUser().getId() : null)
	                    .created(category.getCreated())
	                    .build();
	            return ResponseEntity.ok(updatedCategoryDto);
				
			}catch(RuntimeException e) {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			} 
		 }
		 
		 
		 @DeleteMapping("/categories/{id}")
		 public ResponseEntity<CategoryDto> eliminar(@PathVariable("id") Integer id, @RequestBody CategoryDto categoryDto){
			 serviceCat.eliminar(id);
			 return null;
		 }

}

package com.iapes.iapes.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iapes.iapes.controller.dto.PrivilegeDto;
import com.iapes.iapes.persistence.entity.PrivilegeEntity;
import com.iapes.iapes.service.IPrivilegeService;

@RestController
@RequestMapping("/api")
public class PrivilegeController {
	
	@Autowired
	private IPrivilegeService servicePrivi;
	
	@PostMapping("/post")
	public PrivilegeEntity guardar(@RequestBody PrivilegeEntity privilege) {
		privilege.setCreated(new Date());
		servicePrivi.guardar(privilege);
		return privilege;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Optional <PrivilegeEntity> privilegeOptional = servicePrivi.buscarPorId(id);
		
		if(privilegeOptional.isPresent()) {
			PrivilegeEntity privilege = privilegeOptional.get();
			
			PrivilegeDto privilegeDto = PrivilegeDto.builder()
					
					.id(privilege.getId())
					.dirigeId(privilege.getDirige() != null ? privilege.getDirige().getId() : null)
					.adoracionId(privilege.getAdoracion() != null ? privilege.getAdoracion().getId() : null)
					.alabanzasId(privilege.getAlabanzas() != null ? privilege.getAlabanzas().getId() : null)
					.predicaId(privilege.getPredica() != null ? privilege.getPredica().getId() : null)
					.motivaOfrendaId(privilege.getMotiva_ofrenda() != null ? privilege.getMotiva_ofrenda().getId() : null)
					.recolectaOfrendaId(privilege.getRecolecta_ofrenda() != null ? privilege.getRecolecta_ofrenda().getId() : null)
					.comentarios(privilege.getComentarios())
					.created(privilege.getCreated())
					.build();
			return ResponseEntity.ok(privilegeDto);			
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/geting")
	public List<PrivilegeDto> buscarTodos() {
	    List<PrivilegeEntity> privilegeEntities = servicePrivi.buscarTodos();
	    List<PrivilegeDto> privilegeDTOs = new ArrayList<>();
	    
	    for (PrivilegeEntity privilegeEntity : privilegeEntities) {
	    	PrivilegeDto privilegeDTO = new PrivilegeDto();
	        // Aquí_asigna_los_valores_de_la_entidad_a_la_DTO
	        privilegeDTO.setId(privilegeEntity.getId());
	        //privilegeDTO.setDirigeId(privilegeEntity.getDirige());
	        privilegeDTO.setDirigeId(privilegeEntity.getDirige() != null ? privilegeEntity.getDirige().getId() : null);
	        // Asigna_otros_campos_si_es_necesario
	        
	        privilegeDTOs.add(privilegeDTO);
	    }
	    
	    return privilegeDTOs;
	}
	
}

package com.iapes.iapes.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iapes.iapes.controller.dto.InformationUserDto;
import com.iapes.iapes.controller.dto.PrivilegeDto;
import com.iapes.iapes.persistence.entity.GenderEntity;
import com.iapes.iapes.persistence.entity.InformationUserEntity;
import com.iapes.iapes.persistence.entity.PrivilegeEntity;
import com.iapes.iapes.persistence.entity.SocietieEntity;
import com.iapes.iapes.persistence.entity.StatusMaritalEntity;
import com.iapes.iapes.persistence.entity.TypeUserEntity;
import com.iapes.iapes.persistence.entity.UserEntity;
import com.iapes.iapes.persistence.repository.GenderRepository;
import com.iapes.iapes.persistence.repository.SocietieRepository;
import com.iapes.iapes.persistence.repository.StatusMaritalRepository;
import com.iapes.iapes.persistence.repository.TypeUserRepository;
import com.iapes.iapes.persistence.repository.UserRepository;
import com.iapes.iapes.service.IInformationUserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InformationUserController {
	

	@Autowired
	private IInformationUserService informationUsrServi;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SocietieRepository societyRepository;
	
	@Autowired
	private StatusMaritalRepository statusMariRepository;
	
	@Autowired
	private TypeUserRepository typeUserRepository;
	
	@Autowired
	private GenderRepository genderRepository;
			
	@GetMapping("/users")
	public ResponseEntity<List<InformationUserDto>> buscarTodos(){
		
		List<InformationUserEntity> infoUserEntity =  informationUsrServi.buscarTodos();
		 List<InformationUserDto>infoUserDto =  new ArrayList<>();
		 for(InformationUserEntity informationUserEntity  : infoUserEntity ) {
			 InformationUserDto informationUserDto  = new InformationUserDto();
			 
			 informationUserDto.setId(informationUserEntity.getId());
			 informationUserDto.setName(informationUserEntity.getName());
			 informationUserDto.setLastname(informationUserEntity.getLastname());
			 informationUserDto.setPhone(informationUserEntity.getPhone());
			 informationUserDto.setDui(informationUserEntity.getDui());
			 informationUserDto.setAddress(informationUserEntity.getAddress());
			 informationUserDto.setBirtday(informationUserEntity.getBirtday());
			 informationUserDto.setBirtday(informationUserEntity.getBirtday());
			 informationUserDto.setImg(informationUserEntity.getImg());
			 informationUserDto.setId_users(informationUserEntity.getId_users() != null ? informationUserEntity.getId_users().getId() : null);
			 informationUserDto.setId_society(informationUserEntity.getId_society() != null ? informationUserEntity.getId_society().getId() : null);
			 informationUserDto.setId_status_marital(informationUserEntity.getId_status_marital() != null ? informationUserEntity.getId_status_marital().getId() : null);
			 informationUserDto.setId_type_users(informationUserEntity.getId_type_users() != null ? informationUserEntity.getId_type_users().getId() : null);
			 informationUserDto.setId_gender(informationUserEntity.getId_gender() != null ? informationUserEntity.getId_gender().getId() : null);
 
			 //categoryDtos.add(categoryDto);
			 infoUserDto.add(informationUserDto);			 
		 }
		 
		 return ResponseEntity.ok(infoUserDto);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> guardar(@Valid @RequestBody InformationUserDto informationUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            // Manejo de errores de validación
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
		
		
		InformationUserEntity informationUser = new InformationUserEntity();
		
		informationUser.setName(informationUserDto.getName());
		informationUser.setLastname(informationUserDto.getLastname());
		informationUser.setPhone(informationUserDto.getPhone());
		informationUser.setDui(informationUserDto.getDui());
		informationUser.setAddress(informationUserDto.getAddress());
		informationUser.setBirtday(informationUserDto.getBirtday());
		informationUser.setBirtday(new Date());
		informationUser.setImg(informationUserDto.getImg());
		
		
		// Obtener SocietieEntity por su ID y asignarla a InformationUserEntity
        Integer idSociety = informationUserDto.getId_society();
        Optional<SocietieEntity> optionalSocietieEntity = societyRepository.findById(idSociety);
        SocietieEntity societieEntity = optionalSocietieEntity.orElseThrow(() ->
                new EntityNotFoundException("SocietieEntity not found for id: " + idSociety));
        informationUser.setId_society(societieEntity);
		
        // Obtener id_status_marital 
        Integer idstatusMarital = informationUserDto.getId_status_marital();
        Optional<StatusMaritalEntity> optionalStatusMaritalEntity = statusMariRepository.findById(idstatusMarital);
        StatusMaritalEntity statusMaritalEntity = optionalStatusMaritalEntity.orElseThrow(() ->
                new EntityNotFoundException("idstatusMarital not found for id: " + idstatusMarital));
        informationUser.setId_status_marital(statusMaritalEntity);
		
		//typeUserRepository
        Integer idTypeUser = informationUserDto.getId_type_users();
        Optional<TypeUserEntity> optionalTypeUserEntity = typeUserRepository.findById(idTypeUser);
        TypeUserEntity typeUsrEntity = optionalTypeUserEntity.orElseThrow(() ->
                new EntityNotFoundException("Id_type_users not found for id: " + idTypeUser));
        informationUser.setId_type_users(typeUsrEntity);
        
        
        // Obtener genderEntity 
        Integer idGender = informationUserDto.getId_gender();
        Optional<GenderEntity> optionalGenderEntity = genderRepository.findById(idGender);
        GenderEntity genderEntity = optionalGenderEntity.orElseThrow(() ->
                new EntityNotFoundException("id Gender not found for id: " + idGender));
        informationUser.setId_gender(genderEntity);
        
		// Obtener Id del Usuario automaticamente 
	        UserEntity user = userRepository.findById(informationUserDto.getId_users())
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        
	        
	        informationUser.setId_users(user);
	        informationUsrServi.guardar(informationUser);
	        
	        return ResponseEntity.ok(informationUser);
	
	}

	
	@GetMapping("/users/{id}")
	public  ResponseEntity<?> buscarPorId(@PathVariable Integer id, InformationUserDto informationUserDto){
		
		 Optional <InformationUserEntity> infoUserOptional = informationUsrServi.buscarPorId(id);
		
		if(infoUserOptional.isPresent()) {
			InformationUserEntity inforUsr = infoUserOptional.get();
			
			InformationUserDto infotionUserDto = InformationUserDto.builder()
					
					.id(inforUsr.getId())
					
					.name(inforUsr.getName())
					.lastname(inforUsr.getLastname())
					.dui(inforUsr.getDui())
					.phone(inforUsr.getPhone())
					
					.build();
			return ResponseEntity.ok(infotionUserDto);			
		}
		return ResponseEntity.notFound().build();
		 
	}
	
}

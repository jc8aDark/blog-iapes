package com.iapes.iapes.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PrivilegeDto {
	private Integer id;
	private Integer dirigeId;
	private Integer alabanzasId;
	private Integer adoracionId;
	private Integer predicaId;
	private Integer motivaOfrendaId;
	private Integer recolectaOfrendaId;
	
	private String comentarios;
	private Date created;
}

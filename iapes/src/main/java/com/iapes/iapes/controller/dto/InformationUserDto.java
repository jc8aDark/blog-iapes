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
public class InformationUserDto {
	private Integer  id;
	private String name;
	private String lastname;
	private String phone;
	private String dui;
	private String address;
	private Date birtday;
	private String img;
	
	
	private Integer id_users;
	private Integer id_society;
	private Integer id_status_marital;
	private Integer id_type_users;
	private Integer id_gender;
	
}

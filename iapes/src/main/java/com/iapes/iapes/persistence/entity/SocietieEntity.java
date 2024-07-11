package com.iapes.iapes.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "societies")

public class SocietieEntity {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Integer id;
	//@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Enumerated(EnumType.STRING)
	@Column(name = "name")
    private SocietieEnum societieEnum ;
	
}

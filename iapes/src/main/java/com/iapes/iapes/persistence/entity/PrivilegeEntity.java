package com.iapes.iapes.persistence.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="privileges")

public class PrivilegeEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer id;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "dirige")
private UserEntity dirige;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "alabanzas")
private UserEntity alabanzas;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "adoracion")
private UserEntity adoracion;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "predica")
private UserEntity predica;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "motiva_ofrenda")
private UserEntity motiva_ofrenda;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "recolecta_ofrenda")
private UserEntity recolecta_ofrenda;

private String comentarios;
private Date created;

}

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

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "information_users")

public class InformationUserEntity {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
 private Integer id;
 private String name;
 private String lastname;
 private Date birtday;
 private String phone;
 private String address;
 private String dui;
 private String img;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_users")
 private UserEntity id_users;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_society")
 private SocietieEntity id_society;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_status_marital")
 private StatusMaritalEntity id_status_marital;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_type_users")
 private TypeUserEntity id_type_users;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "id_gender")
 private GenderEntity id_gender;
 
 
}

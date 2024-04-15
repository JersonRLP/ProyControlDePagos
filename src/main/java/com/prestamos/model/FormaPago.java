package com.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formapago")
public class FormaPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFormaPago;
	
	@Column(name= "descripcion")
	private String descripcion;
	
	@Column(name= "estado")
	private int estado;
	
}

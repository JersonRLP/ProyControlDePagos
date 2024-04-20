package com.prestamos.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RolOpcionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRol;
	private int idOpcion;
	



}

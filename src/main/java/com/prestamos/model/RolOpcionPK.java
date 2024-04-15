package com.prestamos.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class RolOpcionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRol;
	private int idOpcion;
	
	public RolOpcionPK() {
	
	}
	
	public RolOpcionPK(int idRol, int idOpcion) {
		this.idRol = idRol;
		this.idOpcion = idOpcion;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}
}

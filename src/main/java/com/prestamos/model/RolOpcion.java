package com.prestamos.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol_has_opcion")
public class RolOpcion {

	@EmbeddedId
	private RolOpcionPK rolOpcionPK;
	
	@ManyToOne
	@JoinColumn(name = "idRol", nullable = false, insertable = false, updatable = false)
	private Rol rol;
	
	@ManyToOne
	@JoinColumn(name = "idOpcion", nullable = false, insertable = false, updatable = false)
	private Opcion opcion;

	public RolOpcion() {

	}

	public RolOpcion(RolOpcionPK rolOpcionPK, Rol rol, Opcion opcion) {
		this.rolOpcionPK = rolOpcionPK;
		this.rol = rol;
		this.opcion = opcion;
	}

	public RolOpcionPK getRolOpcionPK() {
		return rolOpcionPK;
	}

	public void setRolOpcionPK(RolOpcionPK rolOpcionPK) {
		this.rolOpcionPK = rolOpcionPK;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}	
}

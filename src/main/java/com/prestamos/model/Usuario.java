package com.prestamos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column(name = "nombres")
	private String nombres;
	
	@Column(name = "ape_paterno")
	private String apellidoP;
	
	@Column(name = "ape_materno")
	private String apellidoM;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "estado")
	private int estado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRol")
	private Rol rol;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idZona")
	private Zona zona;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuarioLider")
	private Usuario usuariolider;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Usuario getUsuariolider() {
		return usuariolider;
	}

	public void setUsuariolider(Usuario usuariolider) {
		this.usuariolider = usuariolider;
	}

	public Usuario(String nombres, String apellidoP, String apellidoM, String password, String email, String telefono,
			String dni, Rol rol, Zona zona, Usuario usuariolider) {
		super();
		this.nombres = nombres;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
		this.rol = rol;
		this.zona = zona;
		this.usuariolider = usuariolider;
	}
	






	/*public String getNombreCompleto() {
		if (nombres != null && apellidoP != null && apellidoM != null) {
			return nombres.concat(" ").concat(apellidoP).concat(" ").concat(apellidoM);	
		}else {
			return ""; 
		}
	}*/
	
}

package com.prestamos.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private int idUsuario;
	private String username;
	private String nombres;
	private String apePaterno;
	private String apeMaterno;
	private String email;
	private String telefono;
	private String dni;


	
	
}

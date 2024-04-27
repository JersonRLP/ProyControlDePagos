package com.prestamos.service;

import com.prestamos.model.Usuario;

import java.util.List;

public interface PrestatarioService {

    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(Integer id);
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    void eliminar(Integer id);
    List<Usuario> obtenerPrestatariosDelPrestamista(Integer idUsuario);
    List<Usuario> buscarPorNombreYRol(String nombres);
    List<Usuario> buscarPorAtributos(String nombres, String apePaterno, String apeMaterno, String email, String telefono, String dni);
}

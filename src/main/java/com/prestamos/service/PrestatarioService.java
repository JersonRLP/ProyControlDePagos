package com.prestamos.service;

import com.prestamos.model.Usuario;

import java.util.List;

public interface PrestatarioService {

    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(Integer id);
    void guardar(Usuario usuario);
    void actualizar(Usuario usuario);
    void eliminar(Integer id);
}

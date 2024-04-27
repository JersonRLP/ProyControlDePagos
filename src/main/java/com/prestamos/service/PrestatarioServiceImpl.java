package com.prestamos.service;

import com.prestamos.model.Usuario;
import com.prestamos.repository.PrestatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestatarioServiceImpl implements PrestatarioService{

    @Autowired
    private PrestatarioRepository prestatarioRepository;

    @Override
    public List<Usuario> obtenerTodos() {
        return prestatarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return prestatarioRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        prestatarioRepository.save(usuario);

    }

    @Override
    public void actualizar(Usuario usuario) {
        prestatarioRepository.save(usuario);

    }

    @Override
    public void eliminar(Integer id) {
        prestatarioRepository.deleteById(id);

    }

    @Override
    public List<Usuario> obtenerPrestatariosDelPrestamista(Integer idUsuario) {
        return prestatarioRepository.obtenerPrestatariosDelPrestamista(idUsuario);
    }

    @Override
    public List<Usuario> buscarPorNombreYRol(String nombres) {
        Integer idRol = 6; // ID del rol "prestatario"
        return prestatarioRepository.findByNombresAndRolId(nombres, idRol);
    }

    @Override
    public List<Usuario> buscarPorAtributos(String nombres, String apellidoPaterno, String apellidoMaterno, String email, String telefono, String dni) {
        Integer idRol = 6; // ID del rol "prestatario"
        return prestatarioRepository.findByAttributes(nombres, apellidoPaterno, apellidoMaterno, email, telefono, dni, idRol);
    }
}

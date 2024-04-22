package com.prestamos.service;

import com.prestamos.model.Usuario;
import com.prestamos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamistaServiceImpl implements PrestamistaService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);

    }

    @Override
    public void actualizar(Usuario usuario) {
        usuarioRepository.save(usuario);

    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);

    }
}

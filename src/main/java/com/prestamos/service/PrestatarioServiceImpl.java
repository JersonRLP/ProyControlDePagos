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
}

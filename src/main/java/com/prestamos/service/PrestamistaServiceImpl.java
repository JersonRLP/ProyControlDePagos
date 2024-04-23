package com.prestamos.service;

import com.prestamos.model.Usuario;
import com.prestamos.repository.PrestamistaRepository;
import com.prestamos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamistaServiceImpl implements PrestamistaService{

    @Autowired
    private PrestamistaRepository prestamistaRepository;

    @Override
    public List<Usuario> obtenerTodos() {
        return prestamistaRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return prestamistaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        prestamistaRepository.save(usuario);

    }

    @Override
    public void actualizar(Usuario usuario) {
        prestamistaRepository.save(usuario);

    }

    @Override
    public void eliminar(Integer id) {
        prestamistaRepository.deleteById(id);

    }

    /*@Override
    public List<Usuario> obtenerUsuariosPorRol(Integer idRol) {
        return prestamistaRepository.findByRolId(idRol);
    }*/
}

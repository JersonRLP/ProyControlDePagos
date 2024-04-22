package com.prestamos.service;

import com.prestamos.model.Rol;
import com.prestamos.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }
}

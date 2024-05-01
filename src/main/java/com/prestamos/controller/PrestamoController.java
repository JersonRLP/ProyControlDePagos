package com.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.prestamos.model.TipoPrestamoTabla;
import com.prestamos.repository.TipoPrestamoTablaRepository;

@Controller
public class PrestamoController {

	@Autowired
	TipoPrestamoTablaRepository tablarepo;
	
	@GetMapping("/solicitar-prestamo")
	public String solicitarPrestamo(Model model) {
		
		List<TipoPrestamoTabla> tabla = tablarepo.findAll();
		
		model.addAttribute("tabla", tabla);
		
		return "solicitar-prestamo";
	}
	
	@GetMapping("/historial-prestamo")
	public String historialPrestamo()	{
		return "historial-prestamo";
	}
	
}

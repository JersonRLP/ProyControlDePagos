package com.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamos.model.Pago;
import com.prestamos.service.PagoService;

@RestController
@RequestMapping("/pago/")
public class PagoController {

	@Autowired
	PagoService pagoservice;
	
	@GetMapping("/listapagos")
	public List<Pago> listaPagos(){
		return pagoservice.listarPagos();
	}
}

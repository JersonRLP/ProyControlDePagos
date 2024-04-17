package com.prestamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamos.model.Pago;
import com.prestamos.repository.PagoRepository;

@Service
public class PagoService {

	@Autowired
	PagoRepository pagorepo;
	
	public List<Pago> listarPagos(){
		return pagorepo.findAll();
	}
	
}

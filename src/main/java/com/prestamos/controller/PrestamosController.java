package com.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.prestamos.repository.PrestamosRepository;


@Controller
public class PrestamosController {
	

	@Autowired
	private PrestamosRepository prepo;
	
	
	@GetMapping("/prestamos")

	public String cargar(Model model) {
		
		
	    List<Object[]> resultados = prepo.obtenerCalculoMontos();
	    

	    
	    // Imprimir los resultados y el interés
	    for (Object[] resultado : resultados) {
	        Integer dias = (Integer) resultado[0];
	        Double monto1 = (Double) resultado[1];
	        Double monto2 = (Double) resultado[2];
	        Double monto3 = (Double) resultado[3];
	        Double monto4 = (Double) resultado[4];
	        Double monto5 = (Double) resultado[5];
	        Integer interes =(Integer) resultado[6];
	        
	        System.out.println("Días: " + dias);
	        System.out.println("Monto 1: " + monto1);
	        System.out.println("Monto 2: " + monto2);
	        System.out.println("Monto 3: " + monto3);
	        System.out.println("Monto 4: " + monto4);
	        System.out.println("Monto 5: " + monto5);
	        System.out.println("Interes : " + interes);
	    }

	    
	    // Agregar resultados e interés al modelo
	    model.addAttribute("resultados", resultados);
	 
	    
	    return "prestamos";
	}


}

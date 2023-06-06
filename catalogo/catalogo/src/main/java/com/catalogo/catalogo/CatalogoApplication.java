package com.catalogo.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@SpringBootApplication
public class CatalogoApplication {

	@GetMapping("/inicio")
	public String getMnesaje(){
		return "HOLA FUNCIONA O NO FUNCIONA";
	}
	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class,args);
	}


}
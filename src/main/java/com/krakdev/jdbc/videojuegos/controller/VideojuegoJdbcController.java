package com.krakdev.jdbc.videojuegos.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.jdbc.videojuegos.services.ServicioVideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {
	private static final Logger log = LogManager.getLogger(ServicioVideojuegoJdbc.class);
	
	private final ServicioVideojuegoJdbc servicioVideojuegos;
	public VideojuegoJdbcController(ServicioVideojuegoJdbc servicioVideojuegos) {
		this.servicioVideojuegos = servicioVideojuegos;
	}
	
	@PostMapping
	public ResponseEntity<Videojuego>crear(@RequestBody Videojuego videojuego){
		Videojuego creado = servicioVideojuegos.crear(videojuego);
		
		if(creado == null ) {
			return new ResponseEntity<Videojuego>(creado, HttpStatus.CREATED);
		}
		return new ResponseEntity<Videojuego>(creado, HttpStatus.BAD_REQUEST); 
	}
}

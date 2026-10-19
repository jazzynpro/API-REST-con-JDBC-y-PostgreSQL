package com.krakdev.jdbc.videojuegos.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.videojuegos.entidades.Videojuego;
import com.krakedev.jdbc.videojuegos.VideojuegoJdbc;



public class ServicioVideojuegoJdbc{
	private static final Logger log = LogManager.getLogger(ServicioVideojuegoJdbc.class);
	
	public Videojuego crear(Videojuego videojuego) {
		log.info("Servicio crear");
		return VideojuegoJdbc.insertar(videojuego);
		
		
	}
}

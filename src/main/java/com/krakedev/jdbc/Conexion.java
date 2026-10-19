package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Conexion {
	private static final Logger log = LogManager.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "sDCdfU4";

	public static Connection getConnection() {

		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("Conexion exitosa");
			return con;
		} catch (SQLException e) {
			log.error("Error en la conexion: " + e.getMessage());
			throw new RuntimeException("No se pudo conectar", e); // No resuelvo y lo propago para que utilice el metodo
																	// connection resulelva, excepcion tipo tiempo de
																	// ejecucion.
		}
	}
}

package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.videojuegos.entidades.Videojuego;
import com.krakedev.jdbc.Conexion;

public class VideojuegoJdbc {
	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(Videojuego videojuego) {

		Videojuego videojuegoCreado = null;

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
								insert into videojuegos(codigo, nombre, plataforma, precio, disponible, genero)
				values
				(?, ?, ?, ? , ?, ?);
								""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, videojuego.getCodigo());
			ps.setString(1, videojuego.getNombre());
			ps.setString(1, videojuego.getPlataforma());
			ps.setDouble(1, videojuego.getPrecio());
			ps.setBoolean(1, videojuego.isDisponible());
			ps.setString(1, videojuego.getGenero());
			
			int filas = ps.executeUpdate();
			
			videojuegoCreado = videojuego;
			
			log.info("Juego creado con exito");
		} catch (Exception e) {
			log.error("Ocurrio un error al insertar: " + e.getMessage());
		}finally {
			try {
					con.close();
					ps.close();
					log.info("Conexiones cerradas");
				}catch(Exception e) {
					log.error("Ocurrio un error al cerrar las conexiones: " + e.getMessage());
			}
		}
		return videojuegoCreado;
	}
	
}

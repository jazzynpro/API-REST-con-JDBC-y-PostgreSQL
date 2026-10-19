package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
		} finally {
			try {
				con.close();
				ps.close();
				log.info("Conexiones cerradas");
			} catch (Exception e) {
				log.error("Ocurrio un error al cerrar las conexiones: " + e.getMessage());
			}
		}
		return videojuegoCreado;
	}

	public static List<Videojuego> listar() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = """
				select codigo, nombre, plataforma, precio, disponible, genero from videojuegos;
						""";
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego videojuego = new Videojuego();

				videojuego.setCodigo(rs.getString(1));
				videojuego.setNombre(rs.getString(2));
				videojuego.setPlataforma(rs.getString(3));
				videojuego.setPrecio(rs.getDouble(4));
				videojuego.setDisponible(rs.getBoolean(5));
				videojuego.setGenero(rs.getString(6));

				videojuegos.add(videojuego);
			}
			log.info("Filas encontradas: " + videojuegos.size());
		} catch (Exception e) {
			log.error("Error al listar: " + e.getMessage());
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
				log.info("Conexiones cerradas");
			} catch (Exception e) {
				log.error("Ocurrio un error al cerrar las conexiones: " + e.getMessage());
			}
		}
		return videojuegos;
	}
}

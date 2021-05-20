package edu.eci.arsw.cinemApp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinemApp.exceptions.UsuarioException;
import edu.eci.arsw.cinemApp.model.Cinema;
import edu.eci.arsw.cinemApp.model.Pelicula;
import edu.eci.arsw.cinemApp.model.Sala;
import edu.eci.arsw.cinemApp.model.Sede;
import edu.eci.arsw.cinemApp.model.Usuario;

public class CinemAppDB {

	public static final String database = "jdbc:postgresql://ec2-34-195-233-155.compute-1.amazonaws.com:5432/dtv26l9ba2pee";
	public static String usuarioDB = "ilcwmuhkdyxpjg";
	public static String passwordDB = "4e174a257a98600be187fc631c6c8768894ed1108d802b893ab8a73d3217a952";
	public Connection connection = null;

	public void getConnection() throws ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	/* USUARIO */
	
	public Object getUser() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from usuario;");
			String string = null;
			ResultSet resultSet = pstmt.executeQuery();
			Usuario usuario = null;
			while (resultSet.next()) {
				string = resultSet.getString("password");
				usuario = new Usuario(resultSet.getString("username"), resultSet.getString("nombre"),
						resultSet.getString("apellido"), resultSet.getString("correo"),
						resultSet.getString("password"));
				usuarios.add(usuario);
			}
			resultSet.close();
			pstmt.close();
			return usuarios;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	public Object getUserByCorreo(String correo) {
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}

		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from usuario where correo = ?");
			pstmt.setString(1, correo);
			String string = null;
			ResultSet resultSet = pstmt.executeQuery();
			Usuario usuario = null;
			while (resultSet.next()) {
				string = resultSet.getString("password");
				usuario = new Usuario(resultSet.getString("username"), resultSet.getString("nombre"),
						resultSet.getString("apellido"), resultSet.getString("correo"),
						resultSet.getString("password"));
			}
			resultSet.close();
			pstmt.close();
			return usuario;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public void createNewUser(Usuario user) {
		Usuario usuario = user;
		Statement stmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
			} catch (Exception e) {
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			String sql = "INSERT INTO usuario (username,nombre,apellido,correo,password) " + "VALUES ('"
					+ usuario.getUsername() + "','" + usuario.getNombre() + "','" + usuario.getApellido() + "','"
					+ usuario.getCorreo() + "','" + usuario.getPassword() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsers(String email) throws UsuarioException {
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}

		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from usuario where correo = ?");
			pstmt.setString(1, email);
			String string = null;
			ResultSet resultSet = pstmt.executeQuery();
			Usuario usuario = null;
			while (resultSet.next()) {
				string = resultSet.getString("password");
				usuario = new Usuario(resultSet.getString("username"), resultSet.getString("nombre"),
						resultSet.getString("apellido"), resultSet.getString("correo"),
						resultSet.getString("password"));
			}
			resultSet.close();
			pstmt.close();
			return usuario;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;

	}

	/* PELICULAS */

	public List<Pelicula> getPeliculas() {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from pelicula;");
			ResultSet resultSet = pstmt.executeQuery();
			Pelicula pelicula = null;
			while (resultSet.next()) {
				pelicula = new Pelicula(resultSet.getString("nombre"), resultSet.getString("duracion"),
						resultSet.getString("calificacion"), resultSet.getString("horario"),
						resultSet.getString("genero"), resultSet.getString("id"), resultSet.getString("poster"),
						resultSet.getString("director"));
				peliculas.add(pelicula);
			}
			resultSet.close();
			pstmt.close();
			return peliculas;
		} catch (Exception e) {
		}
		return null;
	}

	public List<Pelicula> getPeliculaById(String id) {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from pelicula where id = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			Pelicula pelicula = null;
			while (resultSet.next()) {
				pelicula = new Pelicula(resultSet.getString("nombre"), resultSet.getString("duracion"),
						resultSet.getString("calificacion"), resultSet.getString("horario"),
						resultSet.getString("genero"), resultSet.getString("id"), resultSet.getString("poster"),
						resultSet.getString("director"));
				peliculas.add(pelicula);
			}
			resultSet.close();
			pstmt.close();
			return peliculas;
		} catch (Exception e) {
		}
		return null;
	}
	
	/* CINEMAS */
	
	public List<Cinema> getCinemas() {
		List<Cinema> cinemas = new ArrayList<Cinema>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("select * from cinema;");
			ResultSet resultSet = pstmt.executeQuery();
			Cinema cinema = null;
			while (resultSet.next()) {
				cinema = new Cinema(resultSet.getString("nombre"), resultSet.getString("id"), resultSet.getString("logo"));
				cinemas.add(cinema);
			}
			resultSet.close();
			pstmt.close();
			return cinemas;
		}catch (Exception e) {
		}
		return null;
	}
	
	public List<Cinema> getCinemasById(String idPelicula) {
		List<Cinema> cinemas = new ArrayList<Cinema>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select c.nombre, c.id, c.logo from cinema as c INNER JOIN pelicula_cinema as pc ON c.id=pc.idcinema where pc.idpelicula = ?");
			pstmt.setString(1, idPelicula);
			ResultSet resultSet = pstmt.executeQuery();
			Cinema cinema = null;
			while (resultSet.next()) {
				cinema = new Cinema(resultSet.getString("nombre"), resultSet.getString("id"), resultSet.getString("logo"));
				cinemas.add(cinema);
			}
			resultSet.close();
			pstmt.close();
			return cinemas;
		}catch (Exception e) {
		}
		return null;
	}
	
	public List<Cinema> getCinemasReserva(String cinema, String pelicula) {
		List<Cinema> cinemas = new ArrayList<Cinema>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select c.nombre, c.id, c.logo from cinema as c INNER JOIN pelicula_cinema as pc ON c.id=pc.idcinema where pc.idcinema = '" + cinema + "' and pc.idpelicula = '" + pelicula + "';");
			ResultSet resultSet = pstmt.executeQuery();
			Cinema cine = null;
			while (resultSet.next()) {
				cine = new Cinema(resultSet.getString("nombre"), resultSet.getString("id"), resultSet.getString("logo"));
				cinemas.add(cine);
			}
			resultSet.close();
			pstmt.close();
			return cinemas;
		}catch (Exception e) {
		}
		return null;
	}
	
	/* SEDES */

	public List<Sede> getSede() {
		List<Sede> sedes = new ArrayList<Sede>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("select * from sede;");
			ResultSet resultSet = pstmt.executeQuery();
			Sede sede = null;
			while (resultSet.next()) {
				sede = new Sede(resultSet.getString("nombre"), resultSet.getString("ciudad"), resultSet.getString("ubicacion"), resultSet.getString("horario"), resultSet.getString("id"), resultSet.getString("idCinema"));
				sedes.add(sede);
			}
			resultSet.close();
			pstmt.close();
			return sedes;
		}catch (Exception e) {
		}
		return null;
	}

	public List<Sede> getSedeByID(String idPelicula, String idCinema) {
		List<Sede> sedes = new ArrayList<Sede>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			
			pstmt = connection.prepareStatement("select sede.nombre, sede.ciudad, sede.ubicacion, sede.horario, sede.id, sede.idcinema from sede, pelicula_sede where sede.id = pelicula_sede.idsede and sede.idcinema = '"+ idCinema +"' and pelicula_sede.idpelicula = '"+ idPelicula + "';");
			ResultSet resultSet = pstmt.executeQuery();
			Sede sede = null;
			while (resultSet.next()) {
				sede = new Sede(resultSet.getString("nombre"), resultSet.getString("ciudad"), resultSet.getString("ubicacion"), resultSet.getString("horario"), resultSet.getString("id"), resultSet.getString("idCinema"));
				sedes.add(sede);
			}
			resultSet.close();
			pstmt.close();
			return sedes;
		}catch (Exception e) {
		}
		return null;
	}
	
	public List<Sede> getSedeReserva(String pelicula, String cinema, String sede) {
		List<Sede> sedes = new ArrayList<Sede>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			
			pstmt = connection.prepareStatement("select sede.nombre, sede.ciudad, sede.ubicacion, sede.horario, sede.id, sede.idcinema from sede, pelicula_sede where sede.id = pelicula_sede.idsede and sede.idcinema = '"+ cinema +"' and pelicula_sede.idpelicula = '"+ pelicula + "' and sede.id = '" + sede + "';");
			ResultSet resultSet = pstmt.executeQuery();
			Sede sedota = null;
			while (resultSet.next()) {
				sedota = new Sede(resultSet.getString("nombre"), resultSet.getString("ciudad"), resultSet.getString("ubicacion"), resultSet.getString("horario"), resultSet.getString("id"), resultSet.getString("idCinema"));
				sedes.add(sedota);
			}
			resultSet.close();
			pstmt.close();
			return sedes;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void comprarAsiento(String pelicula, String cinema, String sede, String username, String asientos) {
		Statement stmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			String sql = "INSERT INTO reserva (pelicula, cinema, sede, usuario, asientos) VALUES ('" + pelicula + "','" + cinema + "','" + sede + "','" + username +"','" + asientos + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getReservasByUser(String user) {
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			} catch (Exception e) {

			}
		}try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			
			pstmt = connection.prepareStatement("select * from reserva where usuario = '" + user + "';");
			ResultSet resultSet = pstmt.executeQuery();
			resultSet.close();
			pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteAsiento(String pelicula, String cinema, String sede, String username, String asientos) {
		Statement stmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}try {
			System.out.print("entro DB");
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			String sql = "delete from reserva where usuario = '" +username+ "' and pelicula = '"+ pelicula +"' and cinema = '"+ cinema +"' and sede = '" + sede + "' and asientos = '" + asientos + "';";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

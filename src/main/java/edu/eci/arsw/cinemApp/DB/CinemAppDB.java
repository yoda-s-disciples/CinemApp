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
import edu.eci.arsw.cinemApp.model.Pelicula;
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

	public void createNewUser(Usuario user) {
		Usuario usuario = user;
		Statement stmt = null ;
		if(connection == null) {
			try{
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
			}catch(Exception e) {
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
            String sql = "INSERT INTO usuario (username,nombre,apellido,correo,password) "+"VALUES ('"+usuario.getUsername()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCorreo()+"','"+usuario.getPassword()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
		}catch(Exception e) {
		}
	}

	public Usuario getUsers(String email) throws UsuarioException {
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			}catch(Exception e) {
				
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
			while(resultSet.next()) {
				string = resultSet.getString("password");
				usuario = new Usuario(resultSet.getString("username"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("correo"), resultSet.getString("password"));
			}
			resultSet.close();
			pstmt.close();
			return usuario;
		}catch(Exception e) {
		}
		return null;
		
	}

	public List<Pelicula> getPeliculas() {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(database, usuarioDB, passwordDB);
				connection.setAutoCommit(false);
			}catch(Exception e) {
				
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from pelicula;");
			ResultSet resultSet = pstmt.executeQuery();
			Pelicula pelicula = null;
			while(resultSet.next()) {
				pelicula = new Pelicula(resultSet.getString("nombre"), resultSet.getString("duracion"), resultSet.getString("calificacion"), resultSet.getString("horario"), resultSet.getString("genero"), resultSet.getString("id"), resultSet.getString("poster"), resultSet.getString("director"));
				peliculas.add(pelicula);
			}
			resultSet.close();
			pstmt.close();
			return peliculas;
		}catch(Exception e) {
		}
		return null;
	}

}

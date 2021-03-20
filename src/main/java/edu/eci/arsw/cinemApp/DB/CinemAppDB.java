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
import edu.eci.arsw.cinemApp.model.Usuario;

public class CinemAppDB {
	
	public static final String url = "jdbc:postgresql://ec2-34-195-233-155.compute-1.amazonaws.com:5432/dtv26l9ba2pee?user=ilcwmuhkdyxpjg&password=4e174a257a98600be187fc631c6c8768894ed1108d802b893ab8a73d3217a952";
	private Connection connection;
	private Usuario usuario;
	
    public void getConnection() {
        try {
        	connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
        	e.getMessage();
        }
    }
    
    /* USUARIO */
    
	public List<Usuario> getAllUsers() {
		List<Usuario> users = new ArrayList<Usuario>();
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM usuario");
			connection.close();
			while(resultSet.next()) {
				usuario = new Usuario(resultSet.getString("username"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("correo"), resultSet.getString("password"));
				users.add(usuario);
			}
			stmt.close();
			resultSet.close();
			System.out.println(users);
		}catch(Exception e) {
			Logger.getLogger(CinemAppDB.class.getName()).log(Level.SEVERE, null, e);
		}
		return users;
	}

	public void createNewUser(Usuario user) {
		Usuario usuario = user;
		Statement stmt = null ;
		if(connection == null) {
			try{
				getConnection();
			}catch(Exception e) {
				
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
            String sql = "INSERT INTO usuario (username,nombre,apellido,correo,password)"+"VALUES ('"+usuario.getUsername()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCorreo()+"','"+usuario.getPassword()+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
		}catch(Exception e) {
		}
	}

	public Usuario getUsuarioByUsername(String username) throws UsuarioException {
		PreparedStatement pstmt = null;
		if (connection == null) {
			try {
				getConnection();
				connection.setAutoCommit(false);
			}catch(Exception e) {
				
			}
		}
		
		try {
			Class.forName("org.postgresql.Driver");
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("Select * from usuario where username = ?");
			pstmt.setString(1, username);
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

}

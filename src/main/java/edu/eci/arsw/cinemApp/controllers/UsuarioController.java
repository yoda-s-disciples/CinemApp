package edu.eci.arsw.cinemApp.controllers;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.eci.arsw.cinemApp.exceptions.UsuarioException;
import edu.eci.arsw.cinemApp.model.Usuario;
import edu.eci.arsw.cinemApp.services.UsuarioServices;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServices usuarioServices; 
	
	@RequestMapping(method = RequestMethod.GET, path = "/users/{username:.+}")
	public ResponseEntity<?> getUsers(@PathVariable String username){
		try {
			Usuario user = usuarioServices.getUsers(username);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}catch(UsuarioException e) {
			e.printStackTrace();
			Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/users")
	public ResponseEntity<?> getAllUsers(@RequestBody String usuario) throws NoSuchAlgorithmException{
		try {
			JSONObject obj = new JSONObject(usuario);
			String username = obj.getString("username");
			String password = obj.getString("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] bytes = digest.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
			String psswdHash = sb.toString();
			Usuario user = (Usuario) usuarioServices.getUsers(username);
			if(user.getPassword().equals(psswdHash))
				return new ResponseEntity<>(usuarioServices.getUsers(username), HttpStatus.ACCEPTED);
			else
				return new ResponseEntity<>("Password Incorrecta!", HttpStatus.UNAUTHORIZED);
		}catch (UsuarioException e) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
	}
	
	@RequestMapping(method = RequestMethod.POST , path = "/addUser")
	public ResponseEntity<?> createNewUser(@RequestBody String usuario) throws NoSuchAlgorithmException{
		JSONObject obj = new JSONObject(usuario);
		String password = obj.getString("password1");
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] bytes = digest.digest(password.getBytes());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		String psswdHash = sb.toString();
		Usuario user = new Usuario(obj.getString("username"), obj.getString("nombre"), obj.getString("apellido"), obj.getString("correo"), psswdHash);
		try {
			usuarioServices.createNewUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(UsuarioException e) {
			Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.ALREADY_REPORTED);
		}
		
	}
}

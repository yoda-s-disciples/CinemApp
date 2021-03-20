var apimock = (function(){
	
    var user = {
        username: null,
        nombre: null,
        apellido: null,
        correo: null,
        password: null
    }

    var usuario;
    var username;
    var password;
    var nombre;
    var apellido;
    var correo;
	
	function login(){
        user.username = document.getElementById("login").value;
        user.password = document.getElementById("password").value;
        $.ajax({
            url: "usuario/users",
            type: 'POST',
            data: JSON.stringify(user),
            contentType: 'application/json',
            success: function(){
                location.href = "home.html";
            }
        });
	}
	
    function createUser() {
        user.username = document.getElementById("username").value;
        user.nombre = document.getElementById("nombre").value
        user.apellido = document.getElementById("apellido").value;
        user.correo = document.getElementById("correo").value;
        user.password = document.getElementById("password").value;
        var newUser = "{\"username\":" + username + ",\"nombre\":'" + nombre + "',\"apellido\":'" + apellido + "',\"correo\":'" + correo + "',\password\":'" + password + "'}";
        var crear = $.ajax({
            url: "/usuario/addUser",
            type: 'POST',
            data: JSON.stringify(user),
            contentType: "application/json",
            success: function(){
                location.href = "home.html";
            }
        })
    }
	
	return {
		login : login,
		createUser : createUser
	}
	
})
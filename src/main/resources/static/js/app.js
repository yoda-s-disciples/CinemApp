var app = (function () {

    var user = {
        alias: null,
        name: null,
        lastname: null,
        email: null,
        password1: null
    }

    var usuario;
    var alias;
    var password;
    var password1;
    var name;
    var lastname;
    var email;

    var ingresar = function(){
        user.username = document.getElementById("username").value;
        user.password = document.getElementById("password").value;
        $.ajax({
            url: "/usuario/Users",
            type: 'POST',
            data: JSON.stringify(user),
            contentType: 'application/json',
            success: function () {
                location.href = "home.html";
            }
        });
    }

    var crear = function(){
        user.alias = document.getElementById("username").value;
        user.name = document.getElementById("nombre").value
        user.lastname= document.getElementById("apellido").value;
        user.email = document.getElementById("correo").value;
        user.password1 = document.getElementById("password").value;
        var newUser = "{\"username\":" + alias + ",\"nombre\":'" + name + "',\"apellido\":'" + lastname + "',\"correo\":'" + email + "',\password\":'" + password1 + "'}";
        if((user.email).includes('@')===true){
            if((user.email).includes('.')===true){
                var crear = $.ajax({
                    url: "/usuario/addUser",
                    type: 'POST',
                    data: JSON.stringify(user),
                    contentType: "application/json",
                    success: function () {
                        location.href = "home.html";
                    },
                    error: function () {

                    }
                });
            }
        }
        
    }

    return {
        ingresar : ingresar,
        crear : crear
    }
	
})();
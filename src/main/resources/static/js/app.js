var apiclient = apiclient;
var app = (function () {

    var user = {
        alias: null,
        name: null,
        lastname: null,
        email: null,
        password1: null
    }

    var peliculas = {
        namePelicula: null,
        duracionPelicula: null,
        calificacionPelicula: null,
        horarioPelicula: null,
        generoPelicula: null,
        idPelicula: null,
        posterPelicula: null,
        directorPelicula: null
    }

    var usuario;
    var alias;
    var password;
    var password1;
    var name;
    var lastname;
    var email;

    var ingresar = function(){
        user.email = document.getElementById("username").value;
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
                    }
                });
            }
        }
        
    }

    var getPelicula = function(){
        apiclient.getPelicula(createTable, callback);
    }

    var createTable = function(){
        peliculas = map(peliculas);
        $("#table > tbody").emply();
        peliculas.map(function(peli){
            var idpelicula = '"' + String(peli.id) + '"';
            $("#table > tbody").append(
                "<tr> <td>" +
                peli.namePelicula +
                "</td>" +
                "<td>" +
                peli.duracionPelicula +
                "</td> " +
                "<td>" +
                peli.calificacionPelicula +
                "</td> " +
                "<td>" +
                peli.horarioPelicula +
                "</td> " +
                "<td>" +
                peli.generoPelicula +
                "</td> " +
                "<td>" +
                peli.idPelicula +
                "</td> " +
                "<td>" +
                peli.posterPelicula +
                "</td> " +
                "<td>" +
                peli.directorPelicula +
                "</td> " +
                 "<td><form><button class='btn btn-secondary' type='button' onclick='app.abrirPelicula("+ idpelicula +")'>Abrir</button></form></td>"
                 +
                "</tr>"
            )
        });
    }

    var abrirPelicula = function(){
        location.href = "cinema.html";
    }

    var map = function(list){
        return mapping = list.map(function(peliculas){
            return {
                namePelicula : peliculas.namePelicula,
                duracionPelicula : peliculas.duracionPelicula,
                calificacionPelicula : peliculas.calificacionPelicula,
                horarioPelicula : peliculas.horarioPelicula,
                generoPelicula : peliculas.generoPelicula,
                idPelicula : peliculas.idPelicula,
                posterPelicula : peliculas.posterPelicula,
                directorPelicula : peliculas.directorPelicula
            };
        })
    }

    return {
        ingresar : ingresar,
        crear : crear,
        getPelicula : getPelicula
    }
	
})();
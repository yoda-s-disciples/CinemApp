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

    var ingresar = function () {
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

    var crear = function () {
        user.alias = document.getElementById("username").value;
        user.name = document.getElementById("nombre").value
        user.lastname = document.getElementById("apellido").value;
        user.email = document.getElementById("correo").value;
        user.password1 = document.getElementById("password").value;
        var newUser = "{\"username\":" + alias + ",\"nombre\":'" + name + "',\"apellido\":'" + lastname + "',\"correo\":'" + email + "',\password\":'" + password1 + "'}";
        if ((user.email).includes('@') === true) {
            if ((user.email).includes('.') === true) {
                var crear = $.ajax({
                    url: "/usuario/addUser",
                    type: 'POST',
                    data: JSON.stringify(user),
                    contentType: "application/json",
                    success: function () {
                        location.href = "login.html";
                    }
                });
            }
        }

    }


    var getPelicula = function () {

        $.get("pelicula/Movies", function (peliculon) {
            peliculas = peliculon;
            createTable(peliculon);
        });

    }

    var createTable = function (list) {
        list = map(list);
        $("#table > tbody").empty();
        list.map(function (peliculas) {
            var idpeliculas = '"' + String(peliculas.id) + '"';
            $("#table > tbody").append(
                "<tr> <td>" +
                "<img src='" + peliculas.posterPeliculas + "' width='225' height='300' >" +
                "</td>" +
                "<td>" +
                peliculas.namePeliculas +
                "</td>" +
                "<td>" +
                peliculas.duracionPeliculas +
                "</td> " +
                "<td>" +
                peliculas.generoPeliculas +
                "</td> " +
                "<td>" +
                peliculas.directorPeliculas +
                "</td> " +
                "<td><form><button class='btn btn-secondary' type='button' onclick='app.abrirPelicula(\"" + peliculas.idPeliculas + "\")'>Abrir Cinema</button></form></td>'" +
                "</tr>"
            )
        });
    }

    var abrirPelicula = function (peliculaID) {
        console.info(peliculaID);
        getPeliculaByID(peliculaID);
        
    }
    
    var getPeliculaByID = function (id){
        console.info(id);
        $.get("pelicula/Movies/" + id, function (peliculon) {
            peliculas = peliculon;
            console.info(peliculon);
            createTableByID(peliculon);
        });
    }

    var createTableByID = function (list) {
        list = map2(list);
        $("#table > tbody").empty();
        list.map(function (peliculas) {
            var idpeliculas = '"' + String(peliculas.id) + '"';
            $("#table > tbody").append(
                "<tr> <td>" +
                "<img src='" + peliculas.posterPelicula + "' width='225' height='300' >" +
                "</td>" +
                "<td>" +
                peliculas.namePelicula +
                "</td>" +
                "<td>" +
                peliculas.duracionPelicula +
                "</td> " +
                "<td>" +
                peliculas.generoPelicula +
                "</td> " +
                "<td>" +
                peliculas.directorPelicula +
                "</td> " +
                "</tr>"
            )
        });
    }

    var map2 = function (list) {
        return mapeo = list.map(function(peliculas){
            //console.info(peliculas);
            return{
                namePelicula: peliculas["nombre"],
                duracionPelicula: peliculas["duracion"],
                calificacionPelicula: peliculas["calificacion"],
                horarioPelicula: peliculas["horario"],
                generoPelicula: peliculas["genero"],
                idPelicula: peliculas["id"],
                posterPelicula: peliculas["poster"],
                directorPelicula: peliculas["director"],
            };
        })
    }

    var map = function (list) {
        return mapping = list.map(function (peliculas) {
            return {
                namePeliculas: peliculas["nombre"],
                duracionPeliculas: peliculas["duracion"],
                calificacionPeliculas: peliculas["calificacion"],
                horarioPeliculas: peliculas["horario"],
                generoPeliculas: peliculas["genero"],
                idPeliculas: peliculas["id"],
                posterPeliculas: peliculas["poster"],
                directorPeliculas: peliculas["director"],
            };

        })
    }

    return {
        ingresar: ingresar,
        crear: crear,
        getPelicula: getPelicula,
        createTable: createTable,
        abrirPelicula: abrirPelicula,
        getPeliculaByID: getPeliculaByID,
        createTableByID: createTableByID
    }

})();
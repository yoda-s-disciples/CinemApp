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

    var namePeliculas;
    var duracionPeliculas;
    var calificacionPeliculas;
    var horarioPeliculas;
    var generoPeliculas;
    var idPeliculas;
    var posterPeliculas;
    var directorPeliculas;


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
        //apiclient.getPelicula(createTable);
        $.get("pelicula/Movies", function (peliculon) {
            console.info(peliculon);
            createTable(peliculon);

        });

    }

    var createTable = function (list) {
        list = map(list);
        $("#table > tbody").empty();
        list.map(function (peliculas) {
            var idpelicula = '"' + String(peliculas.id) + '"';
            $("#table > tbody").append(
                "<tr> <td>" +
                peliculas.namePeliculas +
                "</td>" +
                "<td>" +
                peliculas.duracionPeliculas +
                "</td> " +
                "<td>" +
                peliculas.calificacionPeliculas +
                "</td> " +
                "<td>" +
                peliculas.horarioPeliculas +
                "</td> " +
                "<td>" +
                peliculas.generoPeliculas +
                "</td> " +
                "<td>" +
                peliculas.idPeliculas +
                "</td> " +
                "<td>" +
                peliculas.posterPeliculas +
                "</td> " +
                "<td>" +
                peliculas.directorPeliculas +
                "</td> " +
                "<td><form><button class='btn btn-secondary' type='button' onclick='abrirPelicula(" + idpelicula + ")'>Abrir</button></form></td>"
                +
                "</tr>"
            )
        });
    }

    var abrirPelicula = function () {
        location.href = "cinema.html";
    }

    var map = function (list) {
        return mapping = list.map(function (peliculas) {
            return {
                namePeliculas: peliculas.namePelicula,
                duracionPeliculas: peliculas.duracionPelicula,
                calificacionPeliculas: peliculas.calificacionPelicula,
                horarioPeliculas: peliculas.horarioPelicula,
                generoPeliculas: peliculas.generoPelicula,
                idPeliculas: peliculas.idPelicula,
                posterPeliculas: peliculas.posterPelicula,
                directorPeliculas: peliculas.directorPelicula
            };
        })
    }

    return {
        ingresar: ingresar,
        crear: crear,
        getPelicula: getPelicula,
        createTable: createTable
    }

})();
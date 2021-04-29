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

    var cinemas = {
        nameCinema: null,
        idCinema: null,
        logoCinema: null,
        idPelicula: null
    }

    var sedes = {
        nombreSede: null,
        ciudadSede: null,
        ubicacionSede: null,
        horarioSede: null,
        idSede: null,
        idCinema: null,
        nombreCinemaSede: null,
        logoCinemaSede: null
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
                Swal.fire(
                    'Good job!',
                    'Perfecto',
                    'success'
                );
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
                        Swal.fire(
                            'Good job!',
                            'Tu usuario ha sido Registrado!',
                            'success'
                        );
                        location.href = "login.html";
                    }
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'A ocurrido un error, vuelve a intentarlo!',
                    footer: '<a href>Vuelve a intentarlo!</a>'
                })
            };
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'A ocurrido un error, vuelve a intentarlo!',
                footer: '<a href>Vuelve a intentarlo!</a>'
            })
        };

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
                "<td><form><a href='cinema.html?id=" + peliculas.idPeliculas + "'>Seleccionar</a></></td>" +
                "</tr>"
            )
        });
    }

    var createTableCinema = function (list) {
        list = mapCinema(list);
        $("#tableCinema > tbody").empty();
        list.map(function (cinemas) {
            $("#tableCinema > tbody").append(
                "<tr> <td>" +
                "<img src='" + cinemas.logoCinem + "' width='150' height='150' >" +
                "</td>" +
                "<td>" +
                cinemas.nameCinem +
                "</td> " +
                "</tr>"
            )
        });
    }

    var createTableSede = function (list) {
        list = mapSede(list);
        $("#tableSede > tbody").empty();
        list.map(function (sedes) {
            $("#tableSede > tbody").append(
                "<tr> <td>" +
                sedes.nombreSede +
                "</td> " +
                "<td>" +
                sedes.ciudadSede +
                "</td> " +
                "<td> " +
                sedes.ubicacionSede +
                "</td> " +
                "<td> " +
                sedes.horarioSede +
                "</td> " +
                "<td><form><a href='sala.html?id=" + sedes.idSede + "?idP=" + peliculas.idPelicula + "'>Seleccionar</a></></td>" +
                "</tr>"
            )
        });
    }

    var abrirPelicula = function (peliculaID) {
        getPeliculaByID(peliculaID);
    }

    var getPeliculaByID = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var id = urlParams.get('id');
        $.get("pelicula/Movies/" + id, function (peliculon) {
            peliculas = peliculon;
            createTableByID(peliculon);
            getCinemas(id);
            getSedeById(id);
        });

    }

    var getSedeById = function (id) {
        $.get("sede/sedes/" + id, function (sedota) {
            sedes = sedota;
            createTableSede(sedota);
        });
    }

    var getCinemas = function (id) {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        $.get("cinema/Cines/" + id, function (cinem) {
            cinemas = cinem;
            createTableCinema(cinem);
        });
    }

    var createTableByID = function (list) {
        list = map(list);
        $("#tablePelicula > tbody").empty();
        list.map(function (peliculas) {
            var idpeliculas = '"' + String(peliculas.id) + '"';
            $("#tablePelicula > tbody").append(
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
                "</tr>"
            )
        })
        //getCinemas();
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

    var mapCinema = function (list) {
        return mapCine = list.map(function (cinemas) {
            return {
                nameCinem: cinemas["nombre"],
                logoCinem: cinemas["logo"],
                idCinem: cinemas["id"]
            };
        })
    }

    var mapSede = function (list) {
        return mapSede = list.map(function (sedes) {
            console.info(sedes);
            return {
                nombreSede: sedes["nombre"],
                ciudadSede: sedes["ciudad"],
                ubicacionSede: sedes["ubicacion"],
                horarioSede: sedes["horario"],
                idSede: sedes["id"],
                idCinema: sedes["idCinema"],
                nameCinema: sedes["nombreCinemaSede"],
                logoCinema:sedes["logoCinemaSede"]
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
        createTableByID: createTableByID,
        getCinemas: getCinemas,
        createTableCinema: createTableCinema,
        createTableSede: createTableSede,
        getSedeById: getSedeById
    }

})();
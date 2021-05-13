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
        idCinema: null
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
                "<td><form><a href='cinema.html?pelicula=" + peliculas.idPeliculas + "'>Seleccionar</a></></td>" +
                "</tr>"
            )
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
        });
    }

    var createTableCinema = function (list, idPelicula) {
        list = mapCinema(list);
        $("#tableCinema > tbody").empty();
        list.map(function (cinemas) {
            $("#tableCinema > tbody").append(
                "<tr> <td>" +
                "<img src='" + cinemas.logoCinema + "' width='150' height='150' >" +
                "</td>" +
                "<td>" +
                cinemas.nameCinema +
                "</td> " +
                "<td><form><a href='sedes.html?pelicula=" + idPelicula + "&cinema=" + cinemas.idCinema + "'>Seleccionar</a></></td>" +
                "</tr>"
            )
        });

    }

    var createTableSede = function (list, idPelicula, idCinema) {
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
                "<td><form><a href='sala.html?pelicula=" + idPelicula + "&cinema=" + idCinema + "&sede=" + sedes.idSede + "'>Seleccionar</a></></td>" +
                "</tr>"
            )
        });
    }

    var getPeliculaByID = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var idPelicula = urlParams.get('pelicula');
        $.get("pelicula/Movies/" + idPelicula, function (peliculon) {
            peliculas = peliculon;
            createTableByID(peliculon, idPelicula);
            //getCinemas(idPelicula);
            //getSedeById(idPelicula);
        });

    }

    var getCinemas = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var idPelicula = urlParams.get('pelicula');
        $.get("cinema/Cines/" + idPelicula, function (cinem) {
            cinemas = cinem;
            createTableCinema(cinem, idPelicula);
        });
    }

    var getSedeById = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var idPelicula = urlParams.get('pelicula');
        var idCinema = urlParams.get('cinema');
        $.get("sede/sedes/" + idPelicula + "/" + idCinema, function (sedota) {
            sedes = sedota;
            createTableSede(sedota, idPelicula, idCinema);
        });
    }

    var sala = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var sede = urlParams.get('sede');
        var pelicula = urlParams.get('pelicula');
        var cinema = urlParams.get('cinema');
        console.info(sede);
        console.info(pelicula);
        console.info(cinema);
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
                nameCinema: cinemas["nombre"],
                logoCinema: cinemas["logo"],
                idCinema: cinemas["id"]
            };
        })
    }

    var mapSede = function (list) {
        return mapSede = list.map(function (sedes) {
            return {
                nombreSede: sedes["nombre"],
                ciudadSede: sedes["ciudad"],
                ubicacionSede: sedes["ubicacion"],
                horarioSede: sedes["horario"],
                idSede: sedes["id"],
                idCinema: sedes["idCinema"]
            };
        })
    }

    var comprar = function () {
        if ($("input:checked").length == ($("#Numseats").val())) {
            $(".seatStructure *").prop("disabled", true);

            var allSeatsVals = [];

            //Storing in Array
            $('#seatsBlock :checked').each(function () {
                allSeatsVals.push($(this).val());
            });

            //Displaying 
            $('#nameDisplay').val(allNameVals);
            $('#NumberDisplay').val(allNumberVals);
            $('#seatsDisplay').val(allSeatsVals);
            $(".page").attr("hidden", false);

        }
        else {
            Swal.fire("Por favor seleccione:  " + ($("#seatsDisplay").val()) + " asientos")
            console.info("entro");
            console.info('#nameDisplay');
            console.info('#NumberDisplay');
            console.info('#seatsDisplay');
            console.info('#seatsBlock :checked');
            console.info(allSeatsVals);
        }
    }

    return {
        ingresar: ingresar,
        crear: crear,
        getPelicula: getPelicula,
        createTable: createTable,
        getPeliculaByID: getPeliculaByID,
        createTableByID: createTableByID,
        getCinemas: getCinemas,
        createTableCinema: createTableCinema,
        createTableSede: createTableSede,
        getSedeById: getSedeById,
        sala: sala,
        comprar: comprar
    }

})();
var apiclient = apiclient;
var app = (function () {

    var user = {
        nombreUsuario: null,
        apellidoUsuario: null,
        usernameUsiario: null,
        correoUsuario: null,
        passwordUsuario: null
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

    var alias;
    var password;
    var name;
    var lastname;
    var email;
    var estado = false;
    var cryto = "";

    var ingresar = async function () {
        user.correoUsuario = document.getElementById("username").value;
        user.passwordUsuario = document.getElementById("password").value;

        email = user.correoUsuario;
        cryto = user.passwordUsuario;

        const msgBuffer = new TextEncoder('utf-8').encode(cryto);
        const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
        const hashArray = Array.from(new Uint8Array(hashBuffer));
        const password = hashArray.map(b => ('00' + b.toString(16)).slice(-2)).join('');

        $.get("/usuario/correo/" + user.correoUsuario, function (users) {
            user = users;
            if ((email == users.correo) && (password == users.password)) {
                Swal.fire(
                    'Good job!',
                    'Perfecto',
                    'success'
                );
                estado = true;
                console.info(estado);
                sessionStorage.setItem("logged", true);
                setTimeout(function () {
                    window.open("/", "_self");
                }, 2500);
                location.href = "home.html?user=" + users.username + "&estado=" + estado + "";
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'A ocurrido un error, vuelve a intentarlo!',
                    footer: '<a href>Vuelve a intentarlo!</a>'
                })
            }
        });
    }

    var crear = function () {
        user.alias = document.getElementById("username").value;
        user.name = document.getElementById("nombre").value
        user.lastname = document.getElementById("apellido").value;
        user.email = document.getElementById("correo").value;
        user.password1 = document.getElementById("password").value;
        var newUser = "{\"username\":" + alias + ",\"nombre\":'" + name + "',\"apellido\":'" + lastname + "',\"correo\":'" + email + "',\password\":'" + password + "'}";
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

    var cerrarSesion = function () {
        estado = false;
        location.href = "index.html";
        console.info(estado);
    }

    var getPelicula = function (user) {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
        console.info(user);
        console.info(estado);
        $.get("pelicula/Movies", function (peliculon) {
            peliculas = peliculon;
            createTable(peliculon, user, estado);
        });
    }

    var createTable = function (list, user, estado) {
        console.info(user)
        console.info(estado);
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
                "<td><form><a href='cinema.html?user=" + user + "&estado=" + estado + "&pelicula=" + peliculas.idPeliculas + "'>Seleccionar</a></></td>" +
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

    var createTableCinema = function (list, idPelicula, user, estado) {
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
                "<td><form><a href='sedes.html?user=" + user + "&estado=" + estado + "&pelicula=" + idPelicula + "&cinema=" + cinemas.idCinema + "'>Seleccionar</a></></td>" +
                "</tr>"
            )
        });
    }

    var createTableSede = function (list, idPelicula, idCinema, user, estado) {
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
                "<td><form><a href='sala.html?user=" + user + "&estado=" + estado + "&pelicula=" + idPelicula + "&cinema=" + idCinema + "&sede=" + sedes.idSede + "'>Seleccionar</a></></td>" +
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
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
        var idPelicula = urlParams.get('pelicula');
        console.info(user);
        console.info(estado);
        $.get("cinema/Cines/" + idPelicula, function (cinem) {
            cinemas = cinem;
            createTableCinema(cinem, idPelicula, user, estado);
        });
    }

    var getSedeById = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
        var idPelicula = urlParams.get('pelicula');
        var idCinema = urlParams.get('cinema');
        $.get("sede/sedes/" + idPelicula + "/" + idCinema, function (sedota) {
            sedes = sedota;
            createTableSede(sedota, idPelicula, idCinema, user, estado);
        });
    }

    var sala = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var sede = urlParams.get('sede');
        var pelicula = urlParams.get('pelicula');
        var cinema = urlParams.get('cinema');
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
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
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var sede = urlParams.get('sede');
        var pelicula = urlParams.get('pelicula');
        var cinema = urlParams.get('cinema');
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
        var allSeatsVals = [];
        console.info(sede);
        console.info(pelicula);
        console.info(cinema);
        console.info(user);
        console.info(estado);

        //Storing in Array
        $('#seatsBlock :checked').each(function () {
            allSeatsVals.push($(this).val());
        });

        //Displaying 
        //$('#seatsDisplay').val(allSeatsVals);
        console.info(allSeatsVals);
        //Swal.fire("Usted ha seleccionado los siguientes asientos:  " + allSeatsVals)

        var create = $.ajax({
            url: "sede/asientos/" + pelicula + "/" + cinema + "/" + sede + "/"+  user + "/" + allSeatsVals,
            type: 'POST',
            success: function () {
                Swal.fire(
                    'Good job!',
                    'Tu reserva ha sido Registrada!',
                    'success'
                );
                location.href = "info.html?user=" + user + "&estado=" + estado + "&pelicula=" + pelicula + "&cinema=" + cinema + "&sede=" + sede + "&asientos=" + allSeatsVals + "";
            }
        });
    }

    var verReservas = function(){
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var user = urlParams.get('user');
        location.href = "reservas.html?user=" + user + "";

    }


    var getReservasByUsername = function(){
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var user = urlParams.get('user');
        $.get("sede/reservas/" + user, function (reservas) {
            console.info(reservas);
            
        });
    }

    var getReservas = function () {
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var sede = urlParams.get('sede');
        var pelicula = urlParams.get('pelicula');
        var cinema = urlParams.get('cinema');
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
        var asientos = urlParams.get('asientos');
        createTableReserva(pelicula, cinema, sede, user, asientos);
        getPeliculaWithID(pelicula);
        getCinemaWithID(cinema, pelicula);
        getSedeWithID(sede, pelicula, cinema);
    }

    var createTableReserva = function (pelicula, cinema, sede, user, asientos) {
        $("#tableReserva > tbody").empty();
        $("#tableReserva > tbody").append(
            "<tr> <td>" +
            asientos +
            "</td> " +
            "<td><button onclick='app.deleteReserva()'>Eliminar Reserva</button></td>" +
            "</tr>"
        )
    }

    var deleteReserva = function(){
        const valores = window.location.search;
        const urlParams = new URLSearchParams(valores);
        var sede = urlParams.get('sede');
        var pelicula = urlParams.get('pelicula');
        var cinema = urlParams.get('cinema');
        var user = urlParams.get('user');
        var estado = urlParams.get('estado');
        var asientos = urlParams.get('asientos');
        var create = $.ajax({
            url: "sede/delete/" + pelicula + "/" + cinema + "/" + sede + "/"+  user + "/" + asientos,
            type: 'DELETE',
            success: function () {
                Swal.fire(
                    'Good job!',
                    'Tu reserva ha sido Eliminada!',
                    'success'
                );
                location.href = "home.html?user=" + user + "&estado=" + estado + "";
            }
        });
    }


    var getPeliculaWithID = function (pelicula) {
        $.get("pelicula/Movies/" + pelicula, function (peliculon) {
            peliculas = peliculon;
            createTableByWithID(peliculon);
        });
    }

    var createTableByWithID = function (list) {
        list = map(list);
        $("#tablePelicula > tbody").empty();
        list.map(function (peliculas) {
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

    var getCinemaWithID = function (cinema, pelicula) {
        $.get("cinema/reserva/" + cinema + "/" + pelicula, function (cinem) {
            cinemas = cinem;
            createTableCinemaWithID(cinem);
        });
    }

    var createTableCinemaWithID = function (list) {
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
                "</tr>"
            )
        });
    }

    var getSedeWithID = function (sede, pelicula, cinema) {
        $.get("sede/reserva/" + pelicula + "/" + cinema + "/" +sede, function (sedota) {
            sedes = sedota;
            createTableSedeWithID(sedota);
        });
    }

    var createTableSedeWithID = function(list){
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
                "</tr>"
            )
        });
    }

    return {
        ingresar: ingresar,
        crear: crear,
        cerrarSesion: cerrarSesion,
        getPelicula: getPelicula,
        createTable: createTable,
        getPeliculaByID: getPeliculaByID,
        createTableByID: createTableByID,
        getCinemas: getCinemas,
        createTableCinema: createTableCinema,
        createTableSede: createTableSede,
        getSedeById: getSedeById,
        sala: sala,
        comprar: comprar,
        deleteReserva: deleteReserva,
        verReservas: verReservas,
        getReservas: getReservas,
        getReservasByUsername: getReservasByUsername,
        createTableReserva: createTableReserva,
        getPeliculaWithID: getPeliculaWithID,
        getCinemaWithID: getCinemaWithID,
        getSedeWithID, getSedeWithID,
        createTableByWithID: createTableByWithID,
        createTableCinemaWithID: createTableCinemaWithID,
        createTableSedeWithID: createTableSedeWithID

    }

})();
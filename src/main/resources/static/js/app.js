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
		console.log("Peliculón");
		//apiclient.getPelicula(createTable);
		//console.info(peliculas);
		//console.info(Object.values(peliculas));
        
		$.get("pelicula/Movies", function (peliculon) {
			console.log("Peliculas");
			peliculas = peliculon;
			console.info(peliculas);
			console.log("Peliculón");
			console.info(peliculon);
			console.log(peliculon.length)
			console.log(peliculon[0]);
			console.log(Object.values(peliculon[0]));
			console.log(peliculon[0]["nombre"]);
			//peliculas.namePelicula= peliculon[0]["nombre"];
			//namePeliculas= peliculon[0]["nombre"];
			//console.info(peliculas.namePelicula);
			//console.info(namePeliculas);
            createTable(peliculon);

        });

    }

    var createTable = function (list) {
		console.log("list antes de map");
		console.info(list);
        list = map(list);
		console.log("list después de map");
		console.info(list);
		console.log("namePeliculas después del return");
		console.info(namePeliculas);
        $("#table > tbody").empty();
        list.map(function (peliculas) {
            var idpelicula = '"' + String(peliculas.id) + '"';
			console.info(peliculas.namePelicula);
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
                //peliculas.horarioPeliculas +
                //"</td> " +
                //"<td>" +
                peliculas.generoPeliculas +
                "</td> " +
                "<td>" +
                //peliculas.idPeliculas +
                //"</td> " +
                //"<td>" +
                inicio(peliculas.posterPeliculas) +
				//peliculas.posterPeliculas +
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
		console.log("list en map");
		console.info(list);
		console.log("Peliculas en map");
		console.info(peliculas);
		console.info(peliculas.namePelicula);
		console.log("namePeliculas antes del return");
		console.info(namePeliculas);
        return mapping = list.map(function (peliculas) {
			console.log("Peliculas en el return");
			console.log(peliculas);
			console.log(peliculas["nombre"]);
			console.info(peliculas["duracion"]);
			console.info(peliculas["calificacion"]);
			console.info(peliculas["horario"]);
			console.info(peliculas["genero"]);
			console.info(peliculas["id"]);
			console.info(peliculas["poster"]);
			console.info(peliculas["director"]);
			console.info(peliculas.directorPelicula);
            return {
                /*
				namePeliculas: peliculas.namePelicula,
                duracionPeliculas: peliculas.duracionPelicula,
                calificacionPeliculas: peliculas.calificacionPelicula,
                horarioPeliculas: peliculas.horarioPelicula,
                generoPeliculas: peliculas.generoPelicula,
                idPeliculas: peliculas.idPelicula,
                posterPeliculas: peliculas.posterPelicula,
                directorPeliculas: peliculas.directorPelicula,
				*/
				namePeliculas: peliculas["nombre"],
				duracionPeliculas: peliculas["duracion"],
				calificacionPeliculas: peliculas["calificacion"],
				//horarioPeliculas: peliculas["horario"],
				generoPeliculas: peliculas["genero"],
				idPeliculas: peliculas["id"],
				posterPeliculas: peliculas["poster"],
				directorPeliculas: peliculas["director"],
            };
        })
    }
	
	function inicio(url){
		var nuevaImagen = new Image();

        alert("Se procede a la carga en memoria de la imagen");
		return nuevaImagen = cargarImagen(url);
        //return nuevaImagen = cargarImagen("ejemplo.png");
	}

	function cargarImagen(url){
        var imagen = new Image();
        imagen.onload = imagenCargada;
        imagen.src = "img/featured_1.jpg";
        return imagen;
    }

    function imagenCargada(){
        alert("La imagen se ha cargado correctamente");
    }
	  
    return {
        ingresar: ingresar,
        crear: crear,
        getPelicula: getPelicula,
        createTable: createTable
    }

})();
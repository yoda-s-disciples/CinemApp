var apiclient = (function (){

    return {

        getPelicula:function(callback){
            $.get("pelicula/Movies/", function(answ){
                console.info(answ);
                callback(answ);
            });
        }

    }

})();
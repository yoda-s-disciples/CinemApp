var apiclient = (function (){

    return {

        getPelicula:function(callback){
            jquery.get({
                dataType: "json",
                url:"/pelicula/Movies",
                success: function(data)
                {callback(data)
                }
            });
        }

    }

})();
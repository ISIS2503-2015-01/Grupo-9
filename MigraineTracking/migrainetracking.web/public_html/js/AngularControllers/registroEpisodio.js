/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function() {var registroEpisodio = angular.module('registroEpisodio',[]);
    registroEpisodio.directive('episodioInfo',function(){
        return{
            restrict:'E',
            template:'partials/episodioInfo.html',
            controller:['$http',function($http){
                    var self=this;
                    self.episodios = [];
                    $http.get('/registroepisodios/getAll/EpisodiosDolor').success(function(data){
                       self.episodios=data; 
                    });
            }],
            controllerAs:'getEpisodios'
        };
    });
    registroEpisodio.directive('episodioForm', function(){
        return{
            restrict:'E',
            template:'partials/episodioForm.html',
            controller:['$http',function($http){
                    var self=this;
                    self.episodio={};
                    this.createEpisodioDolor =function(){
                        $http.post('/registroepisodios/create/EpisodioDolor/',JSON.stringify(self.episodio)).success(function(data){
                            self.competitor={};
                        });
                    };
            }],
            controllerAs:'createEpisodio'
        };
    });
});

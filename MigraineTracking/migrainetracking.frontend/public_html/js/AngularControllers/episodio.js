/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var app1 = angular.module('episodioDirectives',[]);
    app1.directive('episodioInfo',function(){
        return{
            restrict:'E',
            templateUrl:'partials/episodio/episodio-info.html',
            controller:['$http',function($http){
                    var self = this;
                    self.episodios = [];
                    $http.get('URL').success(function(data){
                        episodios =data;
                    });
            }],
            controllerAs:'getEpisodios'
        };
    });
    app1.directive('episodioDetalle',function(){
        return{
            restrict:'E',
            templateUrl:'partials/episodio/episodio-detalle',
            controller:['$http',function($http){
                    var self = this;
                    self.episodio ={};
                    self.id={};
                    $http.get('URL/'+ self.id).success(function(data){
                        episodio=data;
                    });
            }],
            controllerAs:'getEpisodioDetalle'
        };
        
    });
})();


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
                    $http.get('http://localhost:8080/episodios').success(function(data){
                        console.log(data);
                        self.episodios =data;
                    });
            }],
            controllerAs:'getEpisodios'
        };
    });
    app1.directive('episodioDetalle',function(){
        return{
            restrict:'E',
            templateUrl:'partials/episodio/episodio-detalle.html',
            controller:['$http',function($http){
                    var self = this;
                    self.episodio ={};
                    self.id=0;
                    this.darEpisodioDetalle = function(){
                    $http.get('http://localhost:8080/episodios/'+ self.id).success(function(data){
                        self.episodio=data;
                    });
                };
            }],
            controllerAs:'getEpisodioDetalle'
        };
        
    });
    app1.directive('pacienteEpisodios',function(){
        return{
            restrict:'E',
            templateUrl:'partials/episodio/paciente-episodios.html',
            controller:['$http',function($http){
                    var self = this;
                    self.episodios =[];
                    self.id=0;
                    this.buscarPacienteEpisodios = function( ){
                        console.log(self.id);
                       $http.get('http://localhost:8080/pacientes/episodios/'+ self.id).success(function(data){
                        console.log(data);
                        self.episodios = data;
                       }); 
                    };
            }],
            controllerAs:'getPacienteEpisodios'
        };
        
    });
    app1.directive('pacienteEpisodiosfechas',function(){
        return{
            restrict:'E',
            templateUrl:'partials/episodio/paciente-episodiosfechas.html',
            controller:['$http',function($http){
                    var self = this;
                    self.episodios =[];
                    self.id=0;
                    self.fecha1={};
                    self.fecha2={};
                    this.buscarPacienteEpisodiosFechas = function( ){
                        console.log(self.id);
                        console.log(self.fecha1);
                        console.log(self.fecha2);
                       $http.get('http://localhost:8080/'+self.id+'/'+self.fecha1+'/'+self.fecha2).success(function(data){
                        self.episodios = data;
                        console.log(data);
                       }); 
                    };
            }],
            controllerAs:'getPacienteEpisodiosFechas'
        };
        
    });
    //episodios recientes (No esta en uso)
    app1.directive('episodiosRecientes',function(){
        return{
            restrict:'E',
            templateUrl:'partials/episodio/episodios-recientes.html',
            controller:['$http',function($http){
                    var self = this;
                    self.episodios = [];
                    $http.get('http://localhost:8080/migrainetracking.services/webresources/revisionepisodios/getEpisodios2Dias').success(function(data){
                        self.episodios =data;
                        console.log("Recientes");
                        console.log(data);
                    });
            }],
            controllerAs:'getEpisodiosRecientes'
        };
    });
})();


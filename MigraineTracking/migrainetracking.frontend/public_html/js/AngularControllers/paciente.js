/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var app2 = angular.module('pacienteDirectives',[]);
    app2.directive('pacienteInfo',function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente/paciente-info.html',
            controller:['$http',function($http){
                    var self = this;
                    self.pacientes =[];
                    $http.get('http://localhost:8080/pacientes').success(function(data){
                        self.pacientes =data;
                    });
            }],
            controllerAs:'getPacientes'
        };
    });
    app2.directive('pacienteDetalle', function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente/paciente-detalle.html',
            controller:['$http',function($http){
                var self = this;
                self.paciente={};
                self.id=0;
                this.buscarPacienteDetalle = function(){
                    $http.get('http://localhost:8080/pacientes/'+self.id).success(function(data){
                    self.paciente=data;
                });};
                
            }],
            controllerAs:'getPacienteDetalle'
        };
    });
    
})();


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
                    $http.get('http://localhost:8080/migrainetracking.services/webresources/getAll/Pacientes').success(function(data){
                        pacientes =data;
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
                self.id={};
                $http.get('URL/'+self.id).success(function(data){
                    paciente=data;
                });
            }],
            controllerAs:'getPacienteDetalle'
        };
    });
    
})();


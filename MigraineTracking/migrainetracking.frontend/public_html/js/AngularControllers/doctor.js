/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(){var app = angular.module('doctorDirectives',[]);
     app.directive('doctorInfo',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/doctor-info.html',
            controller:['$http',function($http){
                var self = this;
                self.doctors =[];
                $http.get('http://localhost:8080/migrainetracking.services/webresources/registrousuarios/getAll/Pacientes').success(function(data){
                    doctors=data;
                });
            }],
            controllerAs:'getDoctors'
        }; 
    }); 
    app.directive('doctorDetalle',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/doctor-detalle.html',
            controller:['$http',function($http){
                    var self=this;
                    self.doctor={};
                    self.id={ };
                    this.buscarDoctorDetalle = function( ){
                       $http.get('http://localhost:8080/migrainetracking.services/webresources/registrousuarios/getById/Doctor/'+ id).success(function(data){
                        doctor = data;
                       }); 
                    };
            }],
            controllerAs:'getDoctorDetalle'
        };
    });
    app.directive('doctorPacientes',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/doctor-pacientes.html',
            controller:['$http',function($http){
                    var self=this;
                    self.pacientes=[];
                    self.id={ };
                    this.buscarDoctorPacientes = function( ){
                       $http.get('http://localhost:8080/migrainetracking.services/webresources/registrousuarios/getPacientes/doctorid/'+ id).success(function(data){
                        pacientes = [];
                       }); 
                    };
            }],
            controllerAs:'getDoctorPacientes'
        };
    });
})();

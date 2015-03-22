/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {var app = angular.module('registroPaciente',[]);
    app.directive('pacienteInfo',function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente-info.html',
            controller:['$http',function($http){
                var self =this;
                self.pacientes=[];
                $http.get('../migrainetracking.servicios/registrousuarios/getAll/Pacientes').success(function(data){
                    self.pacientes=data;
                });
            }],
            controllerAs:'getPacientes'
        };
    });
    app.directive('pacienteForm',function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente-form.html',
            controller:['$http',function($http){
                var self=this;
                self.paciente={};
                this.createPaciente=function(){
                     $http.post('/registrousuarios/create/Paciente',JSON.stringify(self.paciente)).success(function(data){
                         self.paciente={};
                     });   
                }; 
            }],
            controllerAs:'createPaciente'
        };
    });
    app.directive('pacienteFormupdate',function(){
       return{
            restrict:'E',
            template:'partials/paciente-formupdate.html',
            controller:['$http',function($http){
                    var self=this;
                    self.paciente={};
                    this.updatePaciente=function(){
                        $http.post('/registrousuarios/update/Paciente',JSON.stringify(self.paciente)).success(function(data){
                            self.paciente={};
                        });
                    };
            }],
            controllerAs:'updatePaciente'
       }; 
    });
})();



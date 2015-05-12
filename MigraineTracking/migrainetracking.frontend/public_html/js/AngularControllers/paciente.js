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
                    var token = localStorage.getItem("token");
                    console.log(localStorage.getItem("token"));
                    $http({
                        method: 'GET',
                        url: 'https://migraine-services.herokuapp.com/webresources/pacientes',
                        headers:{
                           'Content-Type': 'application/json',
                           'x_rest_user':token
                        }
                    }).success(function(data, status, headers, config){
                        self.pacientes =data;
                        var data_hash =headers.data_hash;
                        console.log(verifyIntegrity(data, data_hash));
                    }).error(function(data, status, headers, config){
                        alert("Hubo un error en la transacción");
                    });
                    //$http.get('http://localhost:8080/pacientes').success(function(data){
                        
                   // });
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
                var token = localStorage.getItem("token");
                $http({
                        method: 'GET',
                        url: 'https://migraine-services.herokuapp.com/webresources/pacientes/'+ self.id,
                        headers:{
                           'Content-Type': 'application/json',
                           'x_rest_user':token
                        }
                    }).success(function(data, status, headers, config){
                        self.paciente =data;
                        var data_hash =headers.data_hash;
                        console.log(verifyIntegrity(data, data_hash));
                    }).error(function(data, status, headers, config){
                        alert("Hubo un error en la transacción");
                    });
                //this.buscarPacienteDetalle = function(){
                    //$http.get('http://localhost:8080/pacientes/'+self.id).success(function(data){
                   // self.paciente=data;
                //});};
                
            }],
            controllerAs:'getPacienteDetalle'
        };
    });
    
    function verifyIntegrity(data, data_hash) {
        var new_message_hash = hash_message(data);
        if (new_message_hash === data_hash)
            return true;
        else
            return false;
    };
    
})();


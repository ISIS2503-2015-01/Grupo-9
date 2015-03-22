/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var app2 = angular.module('pacienteDirectives',[]);
    app2.directive('pacienteInfo',function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente-info.html',
            controller:['$http',function($http){
                    var self = this;
                    self.pacientes =[];
                    $http.get('URL').success(function(data){
                        pacientes =data;
                    });
            }],
            controllerAs:'getPacientes'
        };
    });
    
})();


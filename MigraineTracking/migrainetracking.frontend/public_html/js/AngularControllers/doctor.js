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
                $http.get('URL del servicio').success(function(data){
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
                    self.id={};
                    $http.get('URL/'+self.id).success(function(data){
                        doctor = data;
                    });
            }],
            controllerAs:'getDoctorDetalle'
        };
    });
})();

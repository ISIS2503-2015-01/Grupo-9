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
    app.directive('doctorColegas',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/doctor-colegas.html',
            controller: function(){
                this.id=0;
                this.buscarColegasfunction = function(nId){
                    id = nId;
                };
                this.isSelect = function(){
                    return !(isNaN(id)) && id>=0;
                };
            },
            controllerAs:'getColegasDoctor'
        };
    });
    app.directive('darColegas',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/darColegas',
            controller:[],
            controllerAs:'getColegasDoctor'
        };
    });
})();
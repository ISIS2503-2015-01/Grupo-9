/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {var registroDoctor = angular.module('registroDoctor', []);
    registroDoctor.directive('doctorForm', function(){
       return{
           restrict:'E',
           templateUrl:'partials/doctorForm.html',
           controller:['$http', function($http){
              var self = this;
              self.doctor={};
              this.createDoctor=function(){
                  $http.post('/registrousuarios/create/Doctor',JSON.stringify(self.doctor)).success(function(data){
                      self.doctor={};
                  });
              };
           }], 
           controllerAs:'createDoctor'
       };
    });
    registroDoctor.directive('doctorInfo',function(){
       return{
            restrict:'E',
            templateUrl:'partials/doctorInfo.html',
            controller:['$http',function($http){
                var self = this;
                self.doctores = [];
                $http.get('/registrousuarios/getAll/Doctores').success(function(data){
                    self.doctores=data;
                });
            }],
            controllerAs:'getDoctores'
       };
    });
    registroDoctor.directive('doctorFormUpdate',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctorFormUpdate.html',
            controller:['$http',function($http){
                var self = this;
                self.doctor={};
                this.updatePaciente=function(){
                    $http.post('/registrousuarios/update/Doctor',JSON.stringify(self.doctor)).success(function(data){
                        self.doctor={};
                    });
                };
            }],
            controllerAs:'updateDoctor'
        };
    });
});


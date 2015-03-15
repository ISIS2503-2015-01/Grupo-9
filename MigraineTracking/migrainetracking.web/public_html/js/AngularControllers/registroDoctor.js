/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function() {var registroDoctor = angular.module('registroDoctor', []);
    registroDoctor.directive('doctorForm', function(){
       return{
           restrict:'E',
           template:'partials/doctorForm.html',
           controler:['$http', function($http){
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
});


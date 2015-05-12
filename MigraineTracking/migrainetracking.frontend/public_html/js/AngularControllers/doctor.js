/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(){var app = angular.module('doctorDirectives',[]);
    function hash_message( data ){
	var hash  = CryptoJS.SHA512( data ) ;
	var string_bytes  = CryptoJS.enc.Base64.stringify(hash);
	console.log( string );
	return string_bytes;
    };
		
    //Integrity verification for Sha512
    function verifyIntegrity( data , data_hash ){
        var new_message_hash = hash_message( data );
        if(new_message_hash === data_hash)
                return true;
        else
                return false;
    };
     app.directive('doctorInfo',function(){
        return{
            restrict:'E',
            templateUrl:'partials/doctor/doctor-info.html',
            controller:['$http',function($http){
                var self = this;
                self.doctors =[];
                var token = localStorage.getItem("token");
                $http({
                        method: 'GET',
                        url: 'https://migraine-services.herokuapp.com/webresources/doctores',
                        headers:{
                           'Content-Type': 'application/json',
                           'x_rest_user':token
                        }
                    }).success(function(data, status, headers, config){
                        self.doctors =data;
                        var data_hash =headers.data_hash;
                        console.log(verifyIntegrity(data, data_hash));
                        
                    }).error(function(data, status, headers, config){
                        alert("Hubo un error en la transacción");
                    });
                
//                $http.get('https://localhost:8080/webresources/doctores').success(function(data){
//                    console.log(data);
//                    self.doctors=data;
//                });
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
                    self.id=0;
                    var token = localStorage.getItem("token");
                    this.buscarDoctorDetalle = function( ){
                        $http({
                        method: 'GET',
                        url: 'https://migraine-services.herokuapp.com/webresources/doctores/'+self.id,
                        headers:{
                           'Content-Type': 'application/json',
                           'x_rest_user':token
                        }
                    }).success(function(data, status, headers, config){
                        self.doctor =data;
                        var data_hash =headers.data_hash;
                        console.log(verifyIntegrity(data, data_hash));
                    }).error(function(data, status, headers, config){
                        alert("Hubo un error en la transacción");
                    });
                        
//                       $http.get('https://localhost:8080/webresources/doctores/'+ self.id).success(function(data){
//                        self.doctor = data;
//                       }); 
                    };
            }],
            controllerAs:'getDoctorDetalle'
        };
    });
    //No esta en uso
//    app.directive('doctorPacientes',function(){
//        return{
//            restrict:'E',
//            templateUrl:'partials/doctor/doctor-pacientes.html',
//            controller:['$http',function($http){
//                    var self=this;
//                    self.pacientes=[];
//                    self.id=0;
//                    this.buscarDoctorPacientes = function( ){
//                       $http.get('https://localhost:8080/migrainetracking.services/webresources/registrousuarios/getPacientes/doctorid/'+ self.id).success(function(data){
//                        self.pacientes = data;
//                       }); 
//                    };
//            }],
//            controllerAs:'getDoctorPacientes'
//        };
//    });
function verifyIntegrity(data, data_hash) {
        var new_message_hash = hash_message(data);
        if (new_message_hash === data_hash)
            return true;
        else
            return false;
    };
})();
